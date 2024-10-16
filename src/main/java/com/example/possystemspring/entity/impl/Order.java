package com.example.possystemspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    String orderId;
    Date orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
   private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;
    String itemIdOption;
    int orderQty;
    double total;
    double txtCash;
    double txtDiscount;
}
