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

import java.util.*;

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

    @GetMapping("{id}")
    public ResponseEntity<Staff> getStaff(@PathVariable Long id){
        Staff staff = staffService.getById(id);
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public void deleteStaff(@PathVariable Long id){
        staffService.delete(id);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e){
        List<String> errors =  e.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST).timeStamp(new Date()).errors(errors).build();
        return new ResponseEntity<>(errorResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(NoSuchElementException e){
        List<String> errors = Collections.singletonList(e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.NOT_FOUND).timeStamp(new Date()).errors(errors).build();
        return new ResponseEntity<>(errorResponse,new HttpHeaders(),HttpStatus.NOT_FOUND);
    }
}
