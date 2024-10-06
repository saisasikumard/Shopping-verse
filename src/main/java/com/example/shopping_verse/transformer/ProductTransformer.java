package com.example.shopping_verse.transformer;

import com.example.shopping_verse.Enums.Status;
import com.example.shopping_verse.dto.request.ProductRequestDto;
import com.example.shopping_verse.dto.response.ProductResponseDto;
import com.example.shopping_verse.entity.Product;

public class ProductTransformer {
    public static Product productRequestToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .availableQunatity(productRequestDto.getQuanity())
                .category(productRequestDto.getProductCategory())
        .build();
    }
    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .sellerName(product.getSeller().getName())
                .availbleQuantity(product.getAvailableQunatity())
                .build();
    }
}
