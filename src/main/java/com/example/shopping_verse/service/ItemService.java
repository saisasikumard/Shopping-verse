package com.example.shopping_verse.service;

import com.example.shopping_verse.dto.request.ItemRequestDto;
import com.example.shopping_verse.entity.Customer;
import com.example.shopping_verse.entity.Item;
import com.example.shopping_verse.entity.Product;
import com.example.shopping_verse.exceptions.CustomerNotFoundException;
import com.example.shopping_verse.exceptions.InsufficientQuantityException;
import com.example.shopping_verse.exceptions.ProductNotFoundException;
import com.example.shopping_verse.repository.CustomerRepository;
import com.example.shopping_verse.repository.ProductRepository;
import com.example.shopping_verse.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    public Item createItem(ItemRequestDto itemRequestDto){
        Customer customer=customerRepository.findByEmail((itemRequestDto.getCustomerEmail()));
        if(customer==null){
            throw new CustomerNotFoundException(("customer doesnot Exist"));
        }
        Optional<Product> productOptional=productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException(("Product not found"));
        }
        Product product=productOptional.get();
        if(product.getAvailableQunatity()<itemRequestDto.getReqQuantity()){
            throw new InsufficientQuantityException("Sorry,Required Quantity not available");
        }
        Item item= ItemTransformer.itemRequestDtoTOItem(itemRequestDto);
        return item;
    }
}
