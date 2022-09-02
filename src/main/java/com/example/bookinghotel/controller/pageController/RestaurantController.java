package com.example.bookinghotel.controller.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {

    @GetMapping("/restaurant")
    public String restaurantPage(){

        return "restaurant";

    }

}
