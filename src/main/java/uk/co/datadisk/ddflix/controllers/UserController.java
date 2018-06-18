package uk.co.datadisk.ddflix.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.dto.models.PasswordDto;
import uk.co.datadisk.ddflix.entities.PasswordResetToken;
import uk.co.datadisk.ddflix.entities.User;
import uk.co.datadisk.ddflix.exceptions.UserNotFoundException;
import uk.co.datadisk.ddflix.services.ImageService;
import uk.co.datadisk.ddflix.services.MailService;
import uk.co.datadisk.ddflix.services.SecurityUserService;
import uk.co.datadisk.ddflix.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController extends CommonController {

    private final UserService userService;
    private final SecurityUserService securityUserService;
    private final MailService mailService;
    private final ImageService imageService;

    public UserController(UserService userService, SecurityUserService securityUserService, MailService mailService, ImageService imageService) {
        this.userService = userService;
        this.securityUserService = securityUserService;
        this.mailService = mailService;
        this.imageService = imageService;
    }

    @GetMapping({"/", ""})
    public String user() {
        return "user/user";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) {
        System.out.println("Post resetPassword...... email:" + userEmail);
        final User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }

        PasswordResetToken token = userService.createPasswordResetTokenForUser(user);
        SimpleMailMessage email = mailService.constructResetTokenEmail(mailService.getAppUrl(request), request.getLocale(), token.getToken(), user);
        mailService.sendMail(email);
        System.out.println("Email sent to " + user.getEmail());
        return "login";
    }

    @GetMapping("/updatePassword")
    public String updatePasswordGET(@RequestParam("id") final long id, @RequestParam("token") final String token, Model model, PasswordDto passwordDto) {
        final String result = securityUserService.validatePasswordResetToken(id, token);
        if (result != null) {
            return "redirect:/login";
        }

        model.addAttribute("passwordDto", passwordDto);
        return "/user/updatePassword";
    }

    @PostMapping(value = "/updatePassword")
    public String changeUserPassword(@Valid PasswordDto passwordDto) {
        System.out.println("changeUserPassword method......");
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return "/login";
    }

    @GetMapping("/profile")
    public String userProfile(@RequestParam("id") final Long id, Model model) {

        User user = userService.findUser(id);
        model.addAttribute("classActivePayments", true);
        loadModel(model, user);

        return "/user/userProfile";
    }

    @RequestMapping(value = "/profile/changePassword", method = {RequestMethod.GET, RequestMethod.POST})
    public String changeOwnPassword(@RequestParam("id") final Long id, @RequestParam("action") final String action, Model model, PasswordDto passwordDto){

        if(action.equals("view")){
            model.addAttribute("passwordDto", passwordDto);
            return "/user/changePassword";
        } else if (action.equals("save")) {
            User user = userService.findUser(id);
            userService.changeUserPassword(user, passwordDto.getNewPassword());
        }
        return "redirect:/";
    }

    @GetMapping("/profile/uploadProfileImage")
    public String uploadProfileImagePost() {
        return "/user/uploadProfileImage";
    }

    @PostMapping("/profile/uploadProfileImage")
    public String uploadProfileImagePost(@RequestParam("image") MultipartFile profileImageFile, @RequestParam("id") final long id) {
        System.out.println("Saving image " + profileImageFile);
        System.out.println("ID: " + id);

        userService.updateProfileImage(id, profileImageFile);

        return "redirect:/";
    }

    @GetMapping("/profile/{id}/userProfileImage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        User user = userService.findUser(parseLong(id));
        Byte[] profileImage = user.getUserImages().getProfileImage();

        if ( profileImage != null) {
            byte[] byteArray = imageService.imageTobyteConversion(profileImage);
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}