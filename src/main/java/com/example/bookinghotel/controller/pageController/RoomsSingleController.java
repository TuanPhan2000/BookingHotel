package com.example.bookinghotel.controller.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomsSingleController {

    @GetMapping("/roomsSingle")
    public String roomsSinglePage(){

        return "rooms-single";

    }

}
