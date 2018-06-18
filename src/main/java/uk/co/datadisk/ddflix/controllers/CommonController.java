package uk.co.datadisk.ddflix.controllers;

import org.springframework.ui.Model;
import uk.co.datadisk.ddflix.entities.User;

public class CommonController {

    public Model loadModel(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPayments());
        model.addAttribute("userShippingAddressList", user.getUserShippingAddresses());
        model.addAttribute("listOfPayments", true);
        model.addAttribute("listOfShippingAddresses", true);
        return model;
    }
}