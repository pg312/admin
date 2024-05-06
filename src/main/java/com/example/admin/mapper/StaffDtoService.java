package com.example.admin.mapper;

import com.example.admin.model.Role;
import com.example.admin.model.Staff;
import com.example.admin.model.StaffDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class StaffDtoService {

    public StaffDto convert(Staff staff){
        String roles = staff.getRoles().stream().map(Role::getRoleName).toList().toString();

        return StaffDto.builder()
                .id(staff.getId())
                .name(staff.getFirstName()+" "+staff.getMiddleName()+" "+staff.getLastName())
                .role(roles).build();
    }
}
