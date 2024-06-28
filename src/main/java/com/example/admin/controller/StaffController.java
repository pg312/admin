package com.example.admin.controller;

import com.example.admin.mapper.ErrorResponse;
import com.example.admin.mapper.StaffDtoService;
import com.example.admin.model.Staff;
import com.example.admin.service.RabbitMQProducer;
import com.example.admin.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public ResponseEntity<Staff> addStaff(@RequestBody @Valid Staff staff){
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

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e){
        List<String> errors =  e.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST).timeStamp(new Date()).errors(errors).build();
        return new ResponseEntity<>(errorResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
}
