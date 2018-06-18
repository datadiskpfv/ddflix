package uk.co.datadisk.ddflix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.datadisk.ddflix.dto.models.UserRegisterDTO;

@Controller
@RequestMapping("/dataloader")
public class DataLoaderController {

    @GetMapping({"/",""})
    public String registration(UserRegisterDTO userRegisterDTO){

        return "dataloader/dataloader";
    }
}