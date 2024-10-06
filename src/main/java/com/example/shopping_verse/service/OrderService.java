package com.example.shopping_verse.service;

import com.example.shopping_verse.Enums.Status;
import com.example.shopping_verse.dto.request.OrderEntityRequestDto;
import com.example.shopping_verse.dto.response.ItemResponseDto;
import com.example.shopping_verse.dto.response.OrderEntityResponseDto;
import com.example.shopping_verse.entity.*;
import com.example.shopping_verse.exceptions.CustomerNotFoundException;
import com.example.shopping_verse.exceptions.InsufficientQuantityException;
import com.example.shopping_verse.exceptions.InvalidCardException;
import com.example.shopping_verse.exceptions.ProductNotFoundException;
import com.example.shopping_verse.repository.*;
import com.example.shopping_verse.transformer.OrderEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderEntityRepository orderEntityRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CardService cardService;
    public OrderEntity placeOrder(Cart cart, Card card) {
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.cardToMaasked(card.getCardNo()));
        List<Item> items=cart.getItemList();
        int orderTotal=0;
        for(Item item:items){
            Product product=item.getProduct();
            if(product.getAvailableQunatity()<item.getReqQuantity()){
                throw new InsufficientQuantityException("Insufficient quanity..");
            }
            int newQuantity= product.getAvailableQunatity()-item.getReqQuantity();
            product.setAvailableQunatity(newQuantity);
            if(product.getAvailableQunatity()==0){
                product.setStatus(Status.OUT_OF_STOCK);
            }
            orderTotal+=orderEntity.getTotal()+(item.getReqQuantity()*product.getPrice());
            item.setOrderEntity(orderEntity);
        }
        orderEntity.setTotal(orderTotal);
        Date currentDate = new Date(System.currentTimeMillis());
        orderEntity.setDate(currentDate);
        orderEntity.setCustomer(cart.getCustomer());
        orderEntity.setItemList(cart.getItemList());
        return orderEntity;

    }

    public OrderEntityResponseDto placeOrder(OrderEntityRequestDto orderEntityRequestDto) {
        Customer customer=customerRepository.findByEmail(orderEntityRequestDto.getCustmerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid Customer");
        }
        Optional<Product> optionalProduct=productRepository.findById(orderEntityRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Invalid Product");

        }
        Product product=optionalProduct.get();
        Card card=cardRepository.findByCardNo(orderEntityRequestDto.getCardUsed());
        Date currentDate = new Date(System.currentTimeMillis());
        if(card==null||card.getCvv()!= orderEntityRequestDto.getCvv()|| currentDate.after(card.getValidTill())){
            throw new InvalidCardException("Invalid Card");
        }
        if(product.getAvailableQunatity()< orderEntityRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("InSufficinet Quanity..");
        }

        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setDate(currentDate);
        orderEntity.setCardUsed(orderEntityRequestDto.getCardUsed());
        orderEntity.setTotal(product.getPrice()* orderEntityRequestDto.getRequiredQuantity());
        orderEntity.setCustomer(customer);

        orderEntityRepository.save(orderEntity);

        //order to responseDto
        OrderEntityResponseDto orderEntityResponseDto=new OrderEntityResponseDto();
        String maskedcard= cardService.cardToMaasked(orderEntity.getCardUsed());
        orderEntityResponseDto=OrderEntityTransformer.orderToOrderEntityResponseDto(orderEntity,maskedcard);
        Item item=new Item();
        item.setProduct(product);
        item.setCart(null);
        item.setReqQuantity(orderEntityRequestDto.getRequiredQuantity());
        item.setOrderEntity(orderEntity);

        Item savedItem=itemRepository.save(item);
        product.setAvailableQunatity(product.getAvailableQunatity()-orderEntityRequestDto.getRequiredQuantity());
        if(product.getAvailableQunatity()==0){
            product.setStatus(Status.OUT_OF_STOCK);
        }
        productRepository.save(product);
        customer.getOrderEntities().add(orderEntity);
        customerRepository.save(customer);
        ItemResponseDto itemResponseDto=new ItemResponseDto();
        itemResponseDto.setItemName(savedItem.getProduct().getProductName());
        itemResponseDto.setItemPrice(product.getPrice());
        itemResponseDto.setQuantityAdded(orderEntityRequestDto.getRequiredQuantity());
        itemResponseDto.setCategory(product.getCategory());

        orderEntityResponseDto.getItems().add(itemResponseDto);
        return orderEntityResponseDto;

    }
}
