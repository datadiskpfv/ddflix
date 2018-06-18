package uk.co.datadisk.ddflix.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.dto.models.UserPaymentDTO;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.services.UserPaymentService;
import uk.co.datadisk.ddflix.services.UserService;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class PaymentsController extends CommonController {

    private final UserService userService;
    private final UserPaymentService userPaymentService;

    public PaymentsController(UserService userService, UserPaymentService userPaymentService) {
        this.userService = userService;
        this.userPaymentService = userPaymentService;
    }

    // Combines viewing and saving of a default credit card (GET and POST), check the request method
    @RequestMapping(value = "/profile/{userId}/listOfPayments", method = {RequestMethod.GET, RequestMethod.POST})
    public String listOfPayments(@ModelAttribute("defaultUserPaymentId") String defaultUserPaymentId, @PathVariable Long userId, Model model, HttpServletRequest request) {

        User user = userService.findUser(userId);
        model.addAttribute("classActivePayments", true);

        if (request.getMethod().equals(RequestMethod.POST.name())){
            System.out.println("Change default Payment #" + defaultUserPaymentId);
            userPaymentService.setDefault(parseLong(defaultUserPaymentId), user.getId());
        }

        loadModel(model, user);
        return "/user/userProfile";
    }

    // Combines viewing and saving of a credit card (GET and POST), use a action parameter
    @RequestMapping(value = "/profile/{userId}/addNewPayment", method = {RequestMethod.GET, RequestMethod.POST})
    public String addNewCreditCard(@RequestParam("action") String action, @PathVariable Long userId, Model model, UserPaymentDTO userPaymentDTO) {

        User user = userService.findUser(userId);
        model.addAttribute("classActivePayments", true);

        if(action.equals("view")){
            loadModel(model, user);
            model.addAttribute("userPaymentDTO", userPaymentDTO);
            model.addAttribute("addNewPayment", true);
            model.addAttribute("listOfPayments", false);
        } else if (action.equals("save")) {
            System.out.println("Saving the Payment");
            userPaymentDTO.setUser(user);
            userPaymentService.savePayment(userPaymentDTO);

            // We have to refresh the User entity in the session to pickup the changes
            //userService.refreshUserEntity(user);

            loadModel(model, user);
            model.addAttribute("paymentSaved", true);
        }
        return "/user/userProfile";
    }

    @GetMapping("/profile/{userId}/removePayment")
    public String removePayment(@RequestParam("paymentId") Long paymentId, @PathVariable Long userId, Model model, Principal principal){

        // below is another method of getting the user, i could of course have used the id as well.
        System.out.println("Payment ID: " + paymentId);
        System.out.println("user ID: " + userId);

        userPaymentService.removeById(paymentId);

        User user = userService.findByUsername(principal.getName());
        loadModel(model, user);
        model.addAttribute("classActivePayments", true);

        return "/user/userProfile";
    }

    @GetMapping("/profile/{userId}/editPayment")
    public String editPayment(@RequestParam("paymentId") Long paymentId, @PathVariable Long userId, Model model){

        User user = userService.findUser(userId);
        System.out.println("editPayment method cardId: " + paymentId);
        UserPaymentDTO userPaymentDTO = userPaymentService.getUserPaymentDTO(paymentId);
        System.out.println("editPayment method shipping address DTO ID: " + userPaymentDTO.getId());

        loadModel(model, user);

        model.addAttribute("userPaymentDTO", userPaymentDTO);
        model.addAttribute("addNewPayment", true);
        model.addAttribute("classActivePayments", true);
        model.addAttribute("listOfPayments", false);

        return "/user/userProfile";
    }
}
