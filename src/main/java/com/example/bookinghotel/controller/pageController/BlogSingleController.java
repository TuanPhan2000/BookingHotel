package com.example.bookinghotel.controller.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogSingleController {

    @GetMapping("/blogSingle")
    public String blogSinglePage(){

        return "blog-single";

    }

}
