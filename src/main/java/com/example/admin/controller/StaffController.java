package com.example.admin.controller;

import com.example.admin.mapper.StaffDtoService;
import com.example.admin.model.Staff;
import com.example.admin.service.RabbitMQProducer;
import com.example.admin.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @Autowired
    StaffDtoService staffDtoService;

    @PostMapping("add")
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff){
        Staff savedStaff = staffService.add(staff);
        rabbitMQProducer.sendStaffDetails(staffDtoService.convert(staff));
        return new ResponseEntity<>(savedStaff, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Staff>> getAllStaff(){
        List<Staff> staff = staffService.getAll();
        return new ResponseEntity<List<Staff>>(staff,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void deleteStaff(@PathVariable Long id){
        staffService.delete(id);
    }
}
