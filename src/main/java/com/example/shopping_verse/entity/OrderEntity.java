package com.example.shopping_verse.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //contains getter,setter,reqArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="order_entity")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int total;
    @CreationTimestamp
    Date date;
    String orderId;  //UUID

    String cardUsed;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
