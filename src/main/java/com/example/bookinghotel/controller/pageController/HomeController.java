package com.example.bookinghotel.controller.pageController;

import com.example.bookinghotel.service.serviceImpl.CategoryServiceImpl;
import com.example.bookinghotel.service.serviceImpl.RoomServiceImpl;
import com.example.bookinghotel.service.serviceImpl.SizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Controller
public class HomeController {

    @Autowired
    RoomServiceImpl roomServiceImpl;

    @Autowired
    SizeServiceImpl sizeServiceImpl;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;
    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("listCategory", categoryServiceImpl.findAll());
        model.addAttribute("listSize", sizeServiceImpl.findAll());
        return "home";
    }

    @PostMapping("/searchRoom")
    public String searchRoom(@RequestParam("checkin") Date checkin, @RequestParam("checkout") Date checkout,
                             @RequestParam("category") int categoryId, @RequestParam("size") int sizeId, Model model){

        if(roomServiceImpl.findRoomByCategoryIdAndSizeId(categoryId, sizeId) != null)
        {
            model.addAttribute("room", roomServiceImpl.findRoomByCategoryIdAndSizeId(categoryId, sizeId));
            return "rooms-single";
        }

        return "redirect:/";

    }

}
