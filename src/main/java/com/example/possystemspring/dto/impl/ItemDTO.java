package com.example.possystemspring.dto.impl;

import com.example.possystemspring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO implements SuperDTO {
    String id;
    String name;
    double price;
    int qty;
}
