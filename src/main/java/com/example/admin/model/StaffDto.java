package com.example.admin.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffDto {

    private Long id;
    private String name;
    private String roles;

}
