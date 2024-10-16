package com.example.possystemspring.controller;

import com.example.possystemspring.dto.impl.OrderDTO;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vi/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> saveOdrer(@RequestBody OrderDTO orderDTO) {
        try {
                orderService.save(orderDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch(DataPersistException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }