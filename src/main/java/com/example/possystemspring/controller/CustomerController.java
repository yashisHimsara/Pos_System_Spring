package com.example.possystemspring.controller;

import com.example.possystemspring.dao.CrudDAO;
import com.example.possystemspring.dto.impl.CustomerDTO;
import com.example.possystemspring.exception.CustomerNotFoundException;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.possystemspring.util.RegexProcess;
import java.util.List;


@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.save(customerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        try {
            customerService.delete(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomer() {
        return customerService.getAll();
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping(value = "/{custId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void updateCustomer(
//            @RequestPart("name") String name,
//            @RequestPart("address") String address,
//            @RequestPart("salary") double salary,
//            @PathVariable("id") String id
//    ) {
//
//        //Build the Object
//        CustomerDTO buildCustomerDTO = new CustomerDTO();
//        buildCustomerDTO.setId(id);
//        buildCustomerDTO.setName(name);
//        buildCustomerDTO.setAddress(address);
//        buildCustomerDTO.setSalary(salary);
//        customerService.update(id, buildCustomerDTO);
//    }
//
//    @PutMapping(value = "/{customerId}")
//    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDTO customerDTO) {
//        try {
//            customerService.update(customerId, customerDTO);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (CustomerNotFoundException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDTO customerDTO) {
        try {
            customerService.update(customerId, customerDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
