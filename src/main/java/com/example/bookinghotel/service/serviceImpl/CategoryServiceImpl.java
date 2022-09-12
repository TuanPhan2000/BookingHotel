package com.example.bookinghotel.service.serviceImpl;

import com.example.bookinghotel.enity.Category;
import com.example.bookinghotel.repository.CategoryRepository;
import com.example.bookinghotel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
