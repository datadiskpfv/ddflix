package uk.co.datadisk.ddflix.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.dto.models.AddressDTO;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.services.AddressService;
import uk.co.datadisk.ddflix.services.CityService;
import uk.co.datadisk.ddflix.services.UserService;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class AddressController extends CommonController {

    private final UserService userService;
    private final AddressService addressService;
    private final CityService cityService;

    public AddressController(UserService userService, AddressService addressService, CityService cityService) {
        this.userService = userService;
        this.addressService = addressService;
        this.cityService = cityService;
    }

    // Combines viewing and saving of a default credit card (GET and POST), check the request method
    @RequestMapping(value = "/profile/{userId}/listOfShippingAddresses", method = {RequestMethod.GET, RequestMethod.POST})
    public String listOfAddresses(@PathVariable Long userId, Model model, HttpServletRequest request) {

        User user = userService.findUser(userId);
        loadModel(model, user);
        model.addAttribute("classActiveAddresses", true);

//        if (request.getMethod().equals(RequestMethod.POST.name())){
//            addressService.setDefault(parseLong(defaultAddressId), user.getId());
//        }

        return "/user/userProfile";
    }

    // Combines viewing and saving of a credit card (GET and POST), use a action parameter
    @RequestMapping(value = "/profile/{userId}/addNewAddress", method = {RequestMethod.GET, RequestMethod.POST})
    public String addNewAddress(@RequestParam("action") String action, @PathVariable Long userId, Model model, AddressDTO addressDTO) {

        User user = userService.findUser(userId);
        model.addAttribute("classActiveAddresses", true);
        loadModel(model, user);

        if(action.equals("view")){
            //loadModel(model, user);
            model.addAttribute("AddressDTO", addressDTO);
            model.addAttribute("listOfCities", cityService.findAllCities());
            model.addAttribute("addNewAddress", true);
            model.addAttribute("listOfAddresses", false);
        } else if (action.equals("save")) {

            if(user.getAddresses().size() >= 3 && addressDTO.getId() == null) {
                model.addAttribute("maxAddressCount", true);
                //loadModel(model, user);
                return "/user/userProfile";
            }

            System.out.println("Saving address ID:" + addressDTO.getId());
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

    @GetMapping("/profile/{userId}/removeAddress")
    public String removeAddress(@RequestParam("Id") Long Id, @PathVariable Long userId, Model model, Principal principal){

        // below is another method of getting the user, i could of course have used the id as well.
        System.out.println("Shipping ID: " + Id);
        System.out.println("user ID: " + userId);

        addressService.removeById(Id);

        User user = userService.findByUsername(principal.getName());
        loadModel(model, user);
        model.addAttribute("classActiveAddresses", true);

        return "/user/userProfile";
    }

    @GetMapping("/profile/{userId}/editAddress")
    public String editAddress(@RequestParam("Id") Long shippingId, @PathVariable Long userId, Model model){

        User user = userService.findUser(userId);
        System.out.println("editAddress method shippingId: " + shippingId);
        AddressDTO addressDTO = addressService.getAddressDTO(shippingId);
        System.out.println("editAddress method shipping address DTO ID: " + addressDTO.getId());

        loadModel(model, user);

        model.addAttribute("AddressDTO", addressDTO);
        model.addAttribute("addNewAddress", true);
        model.addAttribute("classActiveAddresses", true);
        model.addAttribute("listOfAddresses", false);

        return "/user/userProfile";
    }
}