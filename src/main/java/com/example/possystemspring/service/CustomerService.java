package com.example.possystemspring.service;

import com.example.possystemspring.dao.CrudDAO;
import com.example.possystemspring.dto.CustomerStatus;
import com.example.possystemspring.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService extends CrudDAO<CustomerDTO> {

}
