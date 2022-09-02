package com.example.bookinghotel.service.serviceImpl;

import com.example.bookinghotel.enity.VerificationToken;
import com.example.bookinghotel.repository.VerificationTokenRepository;
import com.example.bookinghotel.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken createToken(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public int checkToken(String token) {
         if(LocalDateTime.now().isBefore(verificationTokenRepository.findVerificationTokenByToken(token).getExpiryDate())){
             return Math.toIntExact(verificationTokenRepository.findVerificationTokenByToken(token).getUser().getId());
         }
         return 0;
    }

    @Override
    public VerificationToken findVerificationTokenByToken(String token) {
        return verificationTokenRepository.findVerificationTokenByToken(token);
    }


}
