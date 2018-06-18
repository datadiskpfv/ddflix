package uk.co.datadisk.ddflix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uk.co.datadisk.ddflix.dto.models.UserRegisterDTO;
import uk.co.datadisk.ddflix.entities.Role;
import uk.co.datadisk.ddflix.services.RoleService;
import uk.co.datadisk.ddflix.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@Controller
public class IndexController {

    private final UserService userService;
    private final RoleService roleService;

    public IndexController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping({"/",""})
    public String root(HttpServletRequest request){

        // there are many other ways to get user information

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        Role adminRole = roleService.findByName("ADMIN");
        Set<Role> roles = (Set<Role>)request.getSession().getAttribute("roles");
        return (roles.contains(adminRole)) ? "redirect:/index" : "redirect:/film/film/carousel";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/registration")
    public String registration(UserRegisterDTO userRegisterDTO, Model model) {
        System.out.println("GET registration.....");
        model.addAttribute("userRegisterDTO", userRegisterDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(Model model, @Valid UserRegisterDTO userRegisterDTO, BindingResult result){
        System.out.println("POST registration.....");
        if(result.hasErrors()){
            System.out.println("Validator registration failed.......");
            return "registration";
        }

        System.out.println("Email: " + userRegisterDTO.getEmail());
        System.out.println("Password: " + userRegisterDTO.getPassword());

        // Make sure that User does not already exist, otherwise return them back to registration with error
        if (userService.checkForExistingUser(userRegisterDTO)){
            userService.createUser(userRegisterDTO);
        } else {
            model.addAttribute("Exists", "exists");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword(){
        return "forgotPassword";
    }
}