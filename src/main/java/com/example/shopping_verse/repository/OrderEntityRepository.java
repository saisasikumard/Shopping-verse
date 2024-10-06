package com.example.shopping_verse.repository;

import com.example.shopping_verse.entity.Cart;
import com.example.shopping_verse.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {
}
