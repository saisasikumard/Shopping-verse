package com.example.shopping_verse.repository;

import com.example.shopping_verse.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}
