package com.example.shopping_verse.service;

import com.example.shopping_verse.dto.response.ProductResponseDto;
import com.example.shopping_verse.entity.Product;
import com.example.shopping_verse.repository.ProductRepository;
import com.example.shopping_verse.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductResponseDto> getAllProducts() {
        List<Product> list=new ArrayList<>();
        list=productRepository.findAll();
        List<ProductResponseDto> productResponseDtos=new ArrayList<>();
        for(Product product:list){
            ProductResponseDto productResponseDto= ProductTransformer.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }
}
