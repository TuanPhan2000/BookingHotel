package com.example.bookinghotel.controller.registerLoginController;

import com.example.bookinghotel.dto.UserDto;
import com.example.bookinghotel.enity.User;
import com.example.bookinghotel.event.OnRegistrationCompleteEvent;
import com.example.bookinghotel.event.RefreshTokenEvent;
import com.example.bookinghotel.service.serviceImpl.UserServiceImpl;
import com.example.bookinghotel.service.serviceImpl.VerificationTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    VerificationTokenServiceImpl verificationTokenServiceImpl;

    @Autowired
    UserServiceImpl usersServiceImpl;

    @PostMapping("/registration")
    public String newUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, RedirectAttributes redirAttrs, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user = userServiceImpl.saveUser(userDto);
        if (user == null) {
            redirAttrs.addFlashAttribute("error", "Email existed!!!");
            return "redirect:register";
        }

      Thread thread = new Thread(() -> {
          try{
              eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale()));
          } catch (Exception ex){
              ex.printStackTrace();
          }
      });

        thread.start();

        redirAttrs.addFlashAttribute("success", "Access email for authentication!!!");
        return "redirect:register";
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token, RedirectAttributes redirAttrs, HttpServletRequest request){
        if(userServiceImpl.activeUser(token, request)){
            redirAttrs.addFlashAttribute("success",  "Account activation successful");
            return "redirect:login";
        } else {
            Thread thread = new Thread(() -> {
                try{
                    User user = userServiceImpl.findUserById(verificationTokenServiceImpl.findVerificationTokenByToken(token).getId());
                    eventPublisher.publishEvent(new RefreshTokenEvent(user, request.getLocale(), token));
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            });
            thread.start();
            redirAttrs.addFlashAttribute("exp",  "Link has expired, we have resubmitted a new link");
            return "redirect:register";
        }

    }

}
