package uk.co.datadisk.ddflix.controllers;

import org.springframework.ui.Model;
import uk.co.datadisk.ddflix.entities.user.User;

public class CommonController {

    public Model loadModel(Model model, User user) {

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPayments());
        model.addAttribute("userAddressList", user.getAddresses());
        //model.addAttribute("listOfPayments", false);
        //model.addAttribute("listOfAddresses", false);

        if (user.getAddresses().size() > 0){
            model.addAttribute("listOfAddresses", true);
        }

        if (user.getUserPayments().size() > 0){
            model.addAttribute("listOfPayments", true);
        }

        return model;
    }
}