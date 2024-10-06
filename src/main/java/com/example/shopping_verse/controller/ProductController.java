package com.example.shopping_verse.controller;

import com.example.shopping_verse.dto.request.ProductRequestDto;
import com.example.shopping_verse.dto.response.ProductResponseDto;
import com.example.shopping_verse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto productResponseDto=productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addQuantity/{id}/{quantity}")
    public ResponseEntity addQuantity(@PathVariable("id") int id,@PathVariable("quantity") int quantity){
        try{
            ProductResponseDto productResponseDto=productService.addQuanity(id,quantity);
            return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
