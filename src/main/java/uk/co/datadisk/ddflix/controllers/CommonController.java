package uk.co.datadisk.ddflix.controllers;

import org.springframework.ui.Model;
import uk.co.datadisk.ddflix.entities.user.User;

public class CommonController {

    public Model loadModel(Model model, User user) {
        model.addAttribute("user", user);
        //model.addAttribute("userPaymentList", user.getUserPayments());
        //model.addAttribute("userShippingAddressList", user.getUserAddresses());
        model.addAttribute("listOfPayments", true);
        model.addAttribute("listOfShippingAddresses", true);
        return model;
    }
}