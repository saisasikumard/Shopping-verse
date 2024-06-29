package com.example.shopping_verse.repository;

import com.example.shopping_verse.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
}
