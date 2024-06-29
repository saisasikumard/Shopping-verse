package com.example.shopping_verse.entity;
import com.example.shopping_verse.Enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.shopping_verse.Enums.ProductCategory;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //contains getter,setter,reqArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String productName;
    @Enumerated(EnumType.STRING)
    ProductCategory category;
    int price;
    int availableQunatity;
    @Enumerated(EnumType.STRING)
    Status status;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> itemList=new ArrayList<>(); //initally empty

    @ManyToOne
    @JoinColumn
    Seller seller;

}
