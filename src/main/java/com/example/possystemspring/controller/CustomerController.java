package com.example.possystemspring.controller;

import com.example.possystemspring.customStatusCodes.SelectedErrorStatus;
import com.example.possystemspring.dto.CustomerStatus;
import com.example.possystemspring.dto.impl.CustomerDTO;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.service.CustomerService;
import com.example.possystemspring.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.possystemspring.util.RegexProcess;


@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> saveCustomer(
            @RequestPart ("name") String name,
            @RequestPart ("address") String address,
            @RequestPart ("salary") double salary
    ) {
        try {
            //CustomerId generate
            String id = AppUtil.generateUserId();
            //Build the Object
            CustomerDTO buildCustomerDTO = new CustomerDTO();
            buildCustomerDTO.setId(id);
            buildCustomerDTO.setName(name);
            buildCustomerDTO.setAddress(address);
            buildCustomerDTO.setSalary(salary);
            customerService.saveCustomer(buildCustomerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedCustomer(@PathVariable ("id") String id){
        if(!RegexProcess.customerIdMatcher(id)){
            return new SelectedErrorStatus(1,"User ID is not valid");
        }
        return customerService.getCustomer(id);
    }
}
