package com.example.shopping_verse.service;

import com.example.shopping_verse.dto.request.CheckoutCartRequestDto;
import com.example.shopping_verse.dto.request.ItemRequestDto;
import com.example.shopping_verse.dto.response.CartResponseDto;
import com.example.shopping_verse.dto.response.OrderEntityResponseDto;
import com.example.shopping_verse.entity.*;
import com.example.shopping_verse.exceptions.CartNullException;
import com.example.shopping_verse.exceptions.CustomerNotFoundException;
import com.example.shopping_verse.exceptions.InvalidCardException;
import com.example.shopping_verse.repository.*;
import com.example.shopping_verse.transformer.CartTransformer;
import com.example.shopping_verse.transformer.OrderEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    ItemService itemService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderEntityRepository orderEntityRepository;
    @Autowired
    CardService cardService;

    public CartResponseDto addToCart(ItemRequestDto itemRequestDto, Item item) {
        Customer customer=customerRepository.findByEmail(itemRequestDto.getCustomerEmail());
        Cart cart=customer.getCart();
        Optional<Product> productOptional=productRepository.findById(itemRequestDto.getProductId());
        Product product=productOptional.get();
        //set Cart Total
        cart.setCartTotal(cart.getCartTotal()+product.getPrice()*itemRequestDto.getReqQuantity());
        //saving the Item and adding to the parent classes to avoid duplicates
        Item savedItem =itemRepository.save(item);
        cart.getItemList().add(savedItem);
        item.setCart(cart);
        item.setProduct(product);
        product.getItemList().add(savedItem);
        productRepository.save(product);
        cartRepository.save(cart);

        //cart to responseDto
        return CartTransformer.cartToCartResponse(cart);

    }
    public OrderEntityResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto){
        Customer customer=customerRepository.findByEmail(checkoutCartRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid Customer");
        }
        //check card
        Card card=cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card==null||card.getCvv()!=checkoutCartRequestDto.getCvv()){
            throw new InvalidCardException("Invalid Card...");
        }
        //check cart
        Cart cart=customer.getCart();
        List<Item> items=cart.getItemList();
        if(items.isEmpty()){
            throw new CartNullException("Cart is Empty");
        }
        //order in order service single point of contact.
        OrderEntity orderEntity=orderService.placeOrder(cart,card);
        customer.getOrderEntities().add(orderEntity);
        resetCart(cart);
        OrderEntity savedOrderEntity=orderEntityRepository.save(orderEntity);

        return OrderEntityTransformer.orderToOrderEntityResponseDto(savedOrderEntity, cardService.cardToMaasked(orderEntity.getCardUsed()));
    }
    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        for(Item item :cart.getItemList()){
            item.setCart(null);
        }
        cart.setItemList(new ArrayList<>());
    }
}
