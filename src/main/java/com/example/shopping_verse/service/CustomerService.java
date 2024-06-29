package com.example.shopping_verse.service;

import com.example.shopping_verse.dto.request.CustomerRequestDto;
import com.example.shopping_verse.dto.response.CustomerResponseDto;
import com.example.shopping_verse.entity.Cart;
import com.example.shopping_verse.entity.Customer;
import com.example.shopping_verse.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shopping_verse.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {


        Customer customer= CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
        Cart cart=new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer savedCustomer=customerRepository.save(customer);

        //entity to Response dto

//        CustomerResponseDto customerResponseDto=new CustomerResponseDto();
//        customerResponseDto.setName(savedCustomer.getName());
//        customerResponseDto.setAddress(savedCustomer.getAddress());
//        customerResponseDto.setAge(savedCustomer.getAge());
//        customerResponseDto.setMobile(savedCustomer.getMobile());
//        customerResponseDto.setGender(savedCustomer.getGender());
//        customerResponseDto.setEmail(savedCustomer.getEmail());

        CustomerResponseDto customerResponseDto=CustomerTransformer.customerToCustomerResponseDto(savedCustomer);



        return customerResponseDto;
    }
}
