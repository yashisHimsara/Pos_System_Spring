package com.example.possystemspring.util;

import com.example.possystemspring.dto.impl.CustomerDTO;
import com.example.possystemspring.entity.impl.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Customer toCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO,Customer.class);
    }
    public CustomerDTO toCustomerDTO(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }
    public List<CustomerDTO> asCustomerDTOList(List<Customer> customerEntites) {
        return modelMapper.map(customerEntites, new TypeToken<List<CustomerDTO>>() {}.getType());
    }
}
