package com.shop.service.Impl;

import com.shop.model.Role;
import com.shop.repository.RoleRepository;
import com.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
