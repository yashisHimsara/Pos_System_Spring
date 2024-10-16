package com.example.possystemspring.dao;

import com.example.possystemspring.entity.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, String> {
}
