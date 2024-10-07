package com.example.shopping_verse.controller;

import com.example.shopping_verse.dto.response.ProductResponseDto;
import com.example.shopping_verse.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    PublicService publicService;
    @GetMapping("/allProducts")
    public ResponseEntity getAllProducts(){
        try{
            List<ProductResponseDto> list=publicService.getAllProducts();
            return new ResponseEntity(list, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
