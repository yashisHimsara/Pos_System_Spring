package com.example.possystemspring.service.impl;

import com.example.possystemspring.customStatusCodes.SelectedErrorStatus;
import com.example.possystemspring.dao.CustomerDao;
import com.example.possystemspring.dto.CustomerStatus;
import com.example.possystemspring.dto.impl.CustomerDTO;
import com.example.possystemspring.entity.impl.Customer;
import com.example.possystemspring.exception.CustomerNotFoundException;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.service.CustomerService;
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
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer saveCustomer = customerDao.save(mapping.toCustomerEntity(customerDTO));
        if (saveCustomer == null) {
            throw new DataPersistException("Customer not saved");
        }
    }
    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerDao.findAll();
        return mapping.asCustomerDTOList(allCustomers);
    }

    @Override
    public CustomerStatus getCustomer(String id) {
        if(customerDao.existsById(id)){
            Customer selectedCustomer = customerDao.getReferenceById(id);
            return mapping.toCustomerDTO(selectedCustomer);
        }else {
            return new SelectedErrorStatus(2, "User with id " + id + " not found");
        }
    }
    @Override
    public void deleteCustomer(String id) {
        Optional<Customer> existedCustomer = customerDao.findById(id);
        if(!existedCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }else {
            customerDao.deleteById(id);
        }
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<Customer> tmpUser = customerDao.findById(id);
        if(tmpUser.isPresent()) {
            tmpUser.get().setName(customerDTO.getName());
            tmpUser.get().setAddress(customerDTO.getAddress());
            tmpUser.get().setSalary(customerDTO.getSalary());
        }
    }
}
