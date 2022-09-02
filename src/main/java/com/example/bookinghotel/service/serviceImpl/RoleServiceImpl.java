package com.example.bookinghotel.service.serviceImpl;

import com.example.bookinghotel.enity.Role;
import com.example.bookinghotel.repository.RoleRepository;
import com.example.bookinghotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
