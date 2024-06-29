package com.example.shopping_verse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.shopping_verse.Enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //contains getter,setter,reqArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="customer")
@Builder
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int age;
    @Column(unique = true)
    String email;

    String address;

    @Column(unique = true)
    String mobile;

    @Enumerated(value = EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> cards=new ArrayList<>();     //initially empty

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntities=new ArrayList<>();   //initially empty

}
