package com.myshop.business.controller;

import com.myshop.business.dto.BusinessResponse;
import com.myshop.business.dto.CreateBusinessRequest;
import com.myshop.business.service.BusinessService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessResponse createBusiness(@Valid @RequestBody CreateBusinessRequest request) {
        return businessService.createBusiness(request);
    }

    @GetMapping("/{businessId}")
    
}
