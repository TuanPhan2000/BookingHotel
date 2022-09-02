package com.example.bookinghotel.service;

import com.example.bookinghotel.enity.VerificationToken;

public interface VerificationTokenService {

    VerificationToken createToken(VerificationToken verificationToken);

    int checkToken(String token);

    VerificationToken findVerificationTokenByToken(String token);


}
