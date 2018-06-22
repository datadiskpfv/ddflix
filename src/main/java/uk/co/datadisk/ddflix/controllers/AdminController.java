package uk.co.datadisk.ddflix.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.dto.models.UserRegisterDTO;
import uk.co.datadisk.ddflix.dto.models.UserEditFormDTO;
import uk.co.datadisk.ddflix.entities.Disc.Disc;
import uk.co.datadisk.ddflix.entities.user.Role;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.services.RoleService;
import uk.co.datadisk.ddflix.services.UserService;
import uk.co.datadisk.ddflix.validators.UserEditFormDTOValidator;

import javax.validation.Valid;
import java.util.List;

import static java.lang.Long.parseLong;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))  // will autowire the NonNull attributes
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @NonNull
    private final UserService userService;

    @NonNull
    private final RoleService roleService;

    @NonNull
    private final UserEditFormDTOValidator userEditFormDTOValidator;

    @GetMapping({"/",""})
    public String admin(){
        return "admin/admin";
    }

    ///////////////////////////
    // User
    ///////////////////////////
    @GetMapping("/user/listUsers")
    public String listUsers(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "admin/user/listUsers";
    }

    @GetMapping("/user/userInfo")
    public String listUsers(@RequestParam("id") Long id, Model model){
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "admin/user/userInfo";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@ModelAttribute("id") String id, Model model) {
        userService.deleteUserById(parseLong(id));
        System.out.println("Deleted user with ID: " + id);
        return "redirect:/admin/user/listUsers";
    }

    @RequestMapping(value = "/user/edit", method = {RequestMethod.GET, RequestMethod.POST})
    public String editUser(@RequestParam("action") String action,  @ModelAttribute("id") String id, Model model, @Valid @ModelAttribute("userEditFormDTO") UserEditFormDTO userEditFormDTO, BindingResult result) {

        if(userEditFormDTO.getEmail() == null) {
            System.out.println("userEditFormDTO is null");
            userEditFormDTO = userService.userForm(parseLong(id));
            List<Role> allRoles = roleService.findAll();
            model.addAttribute("userEditFormDTO", userEditFormDTO);
            model.addAttribute("allRoles", allRoles);
        }

        System.out.println("ID: " + id);
        System.out.println("ACTION: " + action);

        if(action.equals("save")) {

            userEditFormDTOValidator.validate(userEditFormDTO, result);
            if(result.hasErrors()){
                return "/admin/user/userEditForm";
            }

            System.out.println("User " + userEditFormDTO.getEmail() + " saved.");
            System.out.println("User created: " + userEditFormDTO.getDateCreated());
            userService.userFormSave(userEditFormDTO);
            return "redirect:/admin/user/userInfo?id=" + id;
        }
        return "/admin/user/userEditForm";
    }

    @GetMapping("/user/sendFilmsToUsersList")
    public String sendFilmsToUsersList(Model model){
        model.addAttribute("users", userService.findUsersThatNeedFilms());
        return "admin/user/sendFilmsToUsersList";
    }

    @GetMapping("/user/{userId}/sendFilmsToUser")
    public String sendFilmsToUser(@PathVariable Long userId, Model model){
        User user = userService.findUser(userId);
        List<Disc> availableDiscsToSendList = userService.availableDiscsToSend(userId);

        model.addAttribute("user", user);
        model.addAttribute("filmDiscsAtHomeCheck", user.getFilmsAtHomes().size());
        model.addAttribute("availableDiscsListCheck", availableDiscsToSendList.size());

        if( availableDiscsToSendList.size() > 0) {
            model.addAttribute("availableDiscList", availableDiscsToSendList);
        }

        return "admin/user/sendFilmsToUser";
    }

    @GetMapping("/user/{userId}/sendDiscToUser")
    public String sendDiscToUser(@PathVariable Long userId, @RequestParam("filmId") Long filmId){
        userService.sendDiscToUser(userId, filmId);
        return "redirect:/admin/user/" + userId + "/sendFilmsToUser";
    }
}