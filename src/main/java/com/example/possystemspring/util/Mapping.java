package com.example.possystemspring.util;

import com.example.possystemspring.dto.impl.CustomerDTO;
import com.example.possystemspring.dto.impl.ItemDTO;
import com.example.possystemspring.entity.impl.Customer;
import com.example.possystemspring.entity.impl.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

//    Customer
    public Customer toCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO,Customer.class);
    }
    public CustomerDTO toCustomerDTO(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }
//    public List<CustomerDTO> asCustomerDTOList(List<Customer> customerEntites) {
//        return modelMapper.map(customerEntites, new TypeToken<List<CustomerDTO>>() {}.getType());
//    }
    public List<CustomerDTO> asCustomerDTOList(List<Customer> customerList){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer:customerList) {
            customerDTOList.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return customerDTOList;
    }

//    Item
    public Item toItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, Item.class);
    }
    public ItemDTO toItemDTO(Item item){
        return modelMapper.map(item,ItemDTO.class);
    }

//    public List<ItemDTO> asItemDTOList(List<Item> itemEntites) {
//        return modelMapper.map(itemEntites, new TypeToken<List<ItemDTO>>() {}.getType());
//    }
    public List<ItemDTO> asItemDTOList(List<Item> itemList){
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item:itemList) {
            itemDTOList.add(new ItemDTO(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQty()
            ));
        }
        return itemDTOList;
    }
}
