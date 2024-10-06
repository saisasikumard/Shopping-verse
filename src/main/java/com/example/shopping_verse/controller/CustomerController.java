package com.example.shopping_verse.controller;

import com.example.shopping_verse.dto.request.CustomerRequestDto;
import com.example.shopping_verse.dto.response.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.shopping_verse.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto)
    {
        CustomerResponseDto customerResponseDto=customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(customerResponseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getcustomer(@PathVariable("id") int custID){
        try {
            CustomerResponseDto customerResponseDto = customerService.getCustomer(custID);
            return new ResponseEntity(customerResponseDto, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
