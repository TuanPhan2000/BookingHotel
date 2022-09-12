package com.example.bookinghotel.controller.pageController;

import com.example.bookinghotel.service.serviceImpl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomsSingleController {

    @Autowired
    RoomServiceImpl roomServiceImpl;

    @GetMapping("/roomsSingle/{id}")
    public String roomsSinglePage(@PathVariable("id") int id, Model model){
        model.addAttribute("room", roomServiceImpl.findRoomById(id));
        return "rooms-single";

    }

}
