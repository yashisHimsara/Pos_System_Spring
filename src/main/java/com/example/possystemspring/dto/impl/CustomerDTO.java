package com.example.possystemspring.dto.impl;

import com.example.possystemspring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO implements SuperDTO {
    String id;
    String name;
    String address;
    double salary;
}
