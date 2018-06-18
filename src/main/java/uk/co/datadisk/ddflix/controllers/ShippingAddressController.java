package uk.co.datadisk.ddflix.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.dto.models.AddressDTO;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.services.AddressService;
import uk.co.datadisk.ddflix.services.UserService;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class ShippingAddressController extends CommonController {

    private final UserService userService;
    private final AddressService addressService;

    public ShippingAddressController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    // Combines viewing and saving of a default credit card (GET and POST), check the request method
    @RequestMapping(value = "/profile/{userId}/listOfShippingAddresses", method = {RequestMethod.GET, RequestMethod.POST})
    public String listOfShippingAddresses(@ModelAttribute("defaultShippingAddressId") String defaultAddressId, @PathVariable Long userId, Model model, HttpServletRequest request) {

        User user = userService.findUser(userId);
        loadModel(model, user);
        model.addAttribute("classActiveAddresses", true);

        if (request.getMethod().equals(RequestMethod.POST.name())){
            addressService.setDefault(parseLong(defaultAddressId), user.getId());
        }

        return "/user/userProfile";
    }

    // Combines viewing and saving of a credit card (GET and POST), use a action parameter
    @RequestMapping(value = "/profile/{userId}/addNewShippingAddress", method = {RequestMethod.GET, RequestMethod.POST})
    public String addNewShippingAddress(@RequestParam("action") String action, @PathVariable Long userId, Model model, AddressDTO addressDTO) {

        User user = userService.findUser(userId);
        model.addAttribute("classActiveAddresses", true);
        loadModel(model, user);

        if(action.equals("view")){
            //loadModel(model, user);
            model.addAttribute("shippingAddressDTO", addressDTO);
            model.addAttribute("addNewShippingAddress", true);
            model.addAttribute("listOfShippingAddresses", false);
        } else if (action.equals("save")) {

            if(user.getShippingAddresses().size() >= 3 && addressDTO.getId() == null) {
                model.addAttribute("maxAddressCount", true);
                //loadModel(model, user);
                return "/user/userProfile";
            }

            System.out.println("Saving shipping address ID:" + addressDTO.getId());
            addressDTO.setUser(user);
            addressService.saveAddress(addressDTO);

            // We have to refresh the User entity in the session to pickup the changes
            //userService.refreshUserEntity(user);

            model.addAttribute("AddressSaved", true);

            // I need to reload the model so to update the attached data
            loadModel(model,user);
        }
        return "/user/userProfile";
    }

    @GetMapping("/profile/{userId}/removeShippingAddress")
    public String removeShippingAddress(@RequestParam("shippingId") Long shippingId, @PathVariable Long userId, Model model, Principal principal){

        // below is another method of getting the user, i could of course have used the id as well.
        System.out.println("Shipping ID: " + shippingId);
        System.out.println("user ID: " + userId);

        addressService.removeById(shippingId);

        User user = userService.findByUsername(principal.getName());
        loadModel(model, user);
        model.addAttribute("classActiveAddresses", true);

        return "/user/userProfile";
    }

    @GetMapping("/profile/{userId}/editShippingAddress")
    public String editShippingAddress(@RequestParam("shippingId") Long shippingId, @PathVariable Long userId, Model model){

        User user = userService.findUser(userId);
        System.out.println("editShippingAddress method shippingId: " + shippingId);
        AddressDTO addressDTO = addressService.getAddressDTO(shippingId);
        System.out.println("editShippingAddress method shipping address DTO ID: " + addressDTO.getId());

        loadModel(model, user);

        model.addAttribute("shippingAddressDTO", addressDTO);
        model.addAttribute("addNewShippingAddress", true);
        model.addAttribute("classActiveAddresses", true);
        model.addAttribute("listOfShippingAddresses", false);

        return "/user/userProfile";
    }
}
