package com.example.admin.controller;

import com.example.admin.model.Staff;
import com.example.admin.service.StaffService;
import com.oracle.svm.core.annotate.Delete;
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

    @PostMapping("add")
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff){
        Staff savedStaff = staffService.add(staff);
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
