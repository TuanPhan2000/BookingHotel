package com.example.bookinghotel.controller.pageController;

import com.example.bookinghotel.config.MyUserDetails;
import com.example.bookinghotel.enity.Booking;
import com.example.bookinghotel.enity.Room;
import com.example.bookinghotel.enity.User;
import com.example.bookinghotel.service.serviceImpl.BookingServiceImpl;
import com.example.bookinghotel.service.serviceImpl.RoomServiceImpl;
import com.example.bookinghotel.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class RoomsController {

    @Autowired
    RoomServiceImpl roomServiceImpl;

    @Autowired
    BookingServiceImpl bookingServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;
    @GetMapping("/rooms")
    public String roomsPage(Model model){

        model.addAttribute("listRoom", roomServiceImpl.findAll());
        return "rooms";

    }

    @GetMapping("/bookNow/{id}")
    public String bookNow(@PathVariable("id") int roomId, Model model){
        model.addAttribute("roomId", roomId);
        return "booking";
    }

    @PostMapping("/bookingRoom")
    public String book(RedirectAttributes redirectAttributes, @RequestParam("checkin")String checkIn, @RequestParam("checkout") String checkOut, @AuthenticationPrincipal MyUserDetails myUserDetails, @RequestParam("roomId") int roomId){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //convert String to LocalDate
        LocalDate checkin = LocalDate.parse(checkIn, formatter);
        LocalDate checkout = LocalDate.parse(checkOut, formatter);


        User user = userServiceImpl.findUserByEmail(myUserDetails.getUsername());
        Room room = roomServiceImpl.findRoomById(roomId);
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setUser(user);
        booking.setCheckin(checkin);
        booking.setCheckout(checkout);

        if(bookingServiceImpl.booking(booking, roomId)){
            redirectAttributes.addFlashAttribute("success", "Booking Done");
        }
        else {
            redirectAttributes.addFlashAttribute("fail", "Hết phòng");
        }
        return "redirect:bookNow/" + roomId;

    }

}
