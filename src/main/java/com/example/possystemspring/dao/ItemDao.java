package com.example.possystemspring.dao;

import com.example.possystemspring.entity.impl.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item, String> {
}
