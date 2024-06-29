package com.example.shopping_verse.transformer;

import com.example.shopping_verse.dto.request.CustomerRequestDto;
import com.example.shopping_verse.dto.response.CustomerResponseDto;
import com.example.shopping_verse.entity.Customer;

public  class CustomerTransformer {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder().
                name(customerRequestDto.getName()).
                address(customerRequestDto.getAddress()).
                age(customerRequestDto.getAge()).
                email(customerRequestDto.getEmail())
                .mobile(customerRequestDto.getMobile())
                .gender(customerRequestDto.getGender())

                .build();
    }
    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .age(customer.getAge())
                .mobile(customer.getMobile())
                .gender(customer.getGender())
                .email(customer.getEmail()).build();
    }
}
