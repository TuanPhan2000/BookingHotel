package com.example.bookinghotel.service.serviceImpl;

import com.example.bookinghotel.enity.Size;
import com.example.bookinghotel.repository.SizeRepository;
import com.example.bookinghotel.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }
}
