package com.example.admin.mapper;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private HttpStatus status;
    private Date timeStamp;
    private List<String> errors;
}
