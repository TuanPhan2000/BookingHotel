package com.example.bookinghotel.repository;

import com.example.bookinghotel.enity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findVerificationTokenByToken(String token);


}
