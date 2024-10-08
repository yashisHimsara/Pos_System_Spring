package com.example.possystemspring.dto.impl;

import com.example.possystemspring.dto.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO implements CustomerStatus {
    String id;
    String name;
    String address;
    double salary;
}
