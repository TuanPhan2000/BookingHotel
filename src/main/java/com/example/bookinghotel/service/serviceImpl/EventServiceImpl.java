package com.example.bookinghotel.service.serviceImpl;

import com.example.bookinghotel.enity.User;
import com.example.bookinghotel.event.OnRegistrationCompleteEvent;
import com.example.bookinghotel.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    ApplicationEventPublisher eventPublisher;


    @Override
    public boolean sendEmail(User user, Locale locale) {
        Thread sendEmail = new Thread(() -> {
            try{
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, locale));
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        sendEmail.start();
        return true;
    }
}
