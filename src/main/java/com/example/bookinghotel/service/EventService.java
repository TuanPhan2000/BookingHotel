package com.example.bookinghotel.service;

import com.example.bookinghotel.enity.User;

import java.util.Locale;

public interface EventService {

    boolean sendEmail(User user, Locale locale);

}
