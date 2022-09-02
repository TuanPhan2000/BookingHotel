package com.example.bookinghotel.controller.registerLoginController;

import com.example.bookinghotel.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/login")
    public String loginPage(){

        return "login";

    }

}