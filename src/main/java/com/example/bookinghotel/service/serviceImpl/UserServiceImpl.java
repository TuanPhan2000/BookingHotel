package com.example.bookinghotel.service.serviceImpl;

import com.example.bookinghotel.dto.UserDto;
import com.example.bookinghotel.enity.Role;
import com.example.bookinghotel.enity.User;
import com.example.bookinghotel.event.OnRegistrationCompleteEvent;
import com.example.bookinghotel.event.RefreshTokenEvent;
import com.example.bookinghotel.repository.UserRepository;
import com.example.bookinghotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Autowired
    VerificationTokenServiceImpl verificationTokenServiceImpl;


    @Override
    public User saveUser(UserDto userDto) {
        try {
            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            Set<Role> roles = new HashSet<>();
            roles.add(roleServiceImpl.findRoleByName("USER"));
            user.setRoles(roles);
            return userRepository.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean activeUser(String token, HttpServletRequest request) {


        try {
            if (verificationTokenServiceImpl.checkToken(token) != 0) {
                User user = userRepository.findUserById(verificationTokenServiceImpl.checkToken(token));
                user.setEnabled(true);
                userRepository.save(findUserById(verificationTokenServiceImpl.checkToken(token)));
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
