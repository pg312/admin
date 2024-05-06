package com.example.admin.service;

import com.example.admin.model.Role;
import com.example.admin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoles(List<Long> ids) {
        return roleRepository.findAllById(ids);
    }
}
