package com.example.bookinghotel.controller.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomsController {

    @GetMapping("/rooms")
    public String roomsPage(){

        return "rooms";

    }

}
