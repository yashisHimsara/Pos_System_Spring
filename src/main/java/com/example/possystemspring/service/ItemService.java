package com.example.possystemspring.service;

import com.example.possystemspring.dto.ItemStatus;
import com.example.possystemspring.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItem();
    ItemStatus getItem(String id);
    void deleteItem(String id);
    void updateItem(String id, ItemDTO itemDTO);
}
