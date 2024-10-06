package com.example.shopping_verse.repository;

import com.example.shopping_verse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
