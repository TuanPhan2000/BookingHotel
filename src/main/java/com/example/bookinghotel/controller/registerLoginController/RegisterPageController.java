package com.example.bookinghotel.controller.registerLoginController;

import com.example.bookinghotel.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterPageController {

    @GetMapping("/register")
    public String registerPage(Model model){

        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";

    }

}
