package com.example.bookinghotel.event;

import com.example.bookinghotel.enity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class RefreshTokenEvent extends ApplicationEvent {
    private User user;

    private Locale locale;

    private String token;

    public RefreshTokenEvent(User user, Locale locale, String token) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.token = token;
    }
}
