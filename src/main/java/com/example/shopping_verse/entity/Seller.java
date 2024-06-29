package com.example.shopping_verse.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //contains getter,setter,reqArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="seller")
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Column(unique=true)
    String pan;
    int age;
    @Column(unique=true)
    String email;
    @Column(unique = true)
    String mobile;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> productList=new ArrayList<>();
}
