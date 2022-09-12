package com.example.bookinghotel.repository;

import com.example.bookinghotel.enity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {

    List<Size> findAll();

}
