package com.example.possystemspring.service.impl;

import com.example.possystemspring.customStatusCodes.SelectedErrorStatus;
import com.example.possystemspring.dao.CustomerDao;
import com.example.possystemspring.dto.CustomerStatus;
import com.example.possystemspring.dto.impl.CustomerDTO;
import com.example.possystemspring.entity.impl.Customer;
import com.example.possystemspring.exception.CustomerNotFoundException;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.service.CustomerService;
import com.example.possystemspring.util.AppUtil;
import com.example.possystemspring.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void save(CustomerDTO customerDTO) {
        customerDTO.setId(AppUtil.generateCusId());
        Customer saveCustomer = customerDao.save(mapping.toCustomerEntity(customerDTO));
        if (saveCustomer == null) {
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> allCustomers = customerDao.findAll();
        return mapping.asCustomerDTOList(allCustomers);
    }

    @Override
    public CustomerDTO get(String id) {
        if (customerDao.existsById(id)) {
            Customer selectedCustomer = customerDao.getReferenceById(id);
            return mapping.toCustomerDTO(selectedCustomer);
        } else {
            throw new DataPersistException("Customer not found");
        }
    }

    @Override
    public void delete(String id) {
        Optional<Customer> existedCustomer = customerDao.findById(id);
        if (!existedCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        } else {
            customerDao.deleteById(id);
        }
    }
    @Override
    public void update(String id, CustomerDTO customerDTO) {
        Optional<Customer> customer = customerDao.findById(id);
        if (customer == null) {
            throw new DataPersistException("Customer not found");
        } else {
            customer.get().setName(customerDTO.getName());
            customer.get().setAddress(customerDTO.getAddress());
            customer.get().setSalary(customerDTO.getSalary());
        }
    }
}




