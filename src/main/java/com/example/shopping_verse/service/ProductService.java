package com.example.shopping_verse.service;

import com.example.shopping_verse.Enums.Status;
import com.example.shopping_verse.dto.request.ProductRequestDto;
import com.example.shopping_verse.dto.response.ProductResponseDto;
import com.example.shopping_verse.entity.Product;
import com.example.shopping_verse.entity.Seller;
import com.example.shopping_verse.exceptions.ProductNotFoundException;
import com.example.shopping_verse.exceptions.SellerNotFoundException;
import com.example.shopping_verse.repository.ProductRepository;
import com.example.shopping_verse.repository.SellerRepository;
import com.example.shopping_verse.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Optional<Seller> optionalSeller=sellerRepository.findById(productRequestDto.getSellerId());
        if(optionalSeller.isEmpty()){
            throw new SellerNotFoundException("Invalid Seller");
        }
        Seller seller=optionalSeller.get();
        Product product= ProductTransformer.productRequestToProduct(productRequestDto);
        product.setSeller(seller);
        if(product.getAvailableQunatity()>0){
            product.setStatus(Status.AVAILABLE);
        }

        Product savedProduct=productRepository.save(product);
        ProductResponseDto productResponseDto=ProductTransformer.productToProductResponseDto(savedProduct);
        productResponseDto.setAvailbleQuantity(product.getAvailableQunatity());
        return productResponseDto;

    }

    public ProductResponseDto addQuanity(int id, int quantity) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Invalid Product");
        }
        Product product=optionalProduct.get();
        product.setAvailableQunatity(product.getAvailableQunatity()+quantity);
        if(product.getAvailableQunatity()>0){
            product.setStatus(Status.AVAILABLE);
        }
        Product savedProduct =productRepository.save(product);
        return ProductTransformer.productToProductResponseDto(savedProduct);
    }
}
