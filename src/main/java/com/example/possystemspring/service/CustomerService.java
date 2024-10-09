package com.example.possystemspring.service;

import com.example.possystemspring.dto.CustomerStatus;
import com.example.possystemspring.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    CustomerStatus getCustomer(String id);
    void deleteCustomer(String id);
    void updateCustomer(String id, CustomerDTO customerDTO);

}
