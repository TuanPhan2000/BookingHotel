package com.example.bookinghotel.service;


import com.example.bookinghotel.dto.UserDto;
import com.example.bookinghotel.enity.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    User saveUser(UserDto userDto);

    boolean activeUser(String token, HttpServletRequest request);

    User findUserById(long id);

    User findUserByEmail(String email);

}
