package com.example.admin.mapper;

import com.example.admin.model.Role;
import com.example.admin.model.Staff;
import com.example.admin.model.StaffDto;
import com.example.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffDtoService {

    @Autowired
    RoleService roleService;

    public StaffDto convert(Staff staff){

        List<Long> roleIds = staff.getRoles().stream().map(Role::getId).toList();
        String roles = roleService.getRoles(roleIds).stream().map(Role::getRoleName).collect(Collectors.joining(","));

        return StaffDto.builder()
                .id(staff.getId())
                .name(staff.getFirstName()+" "+staff.getMiddleName()+" "+staff.getLastName())
                .roles(roles).build();
    }
}
