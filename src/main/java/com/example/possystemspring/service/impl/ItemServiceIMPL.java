package com.example.possystemspring.service.impl;

import com.example.possystemspring.customStatusCodes.SelectedErrorStatus;
import com.example.possystemspring.dao.ItemDao;
import com.example.possystemspring.dto.ItemStatus;
import com.example.possystemspring.dto.impl.ItemDTO;
import com.example.possystemspring.entity.impl.Item;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.exception.ItemNotFoundException;
import com.example.possystemspring.service.ItemService;
import com.example.possystemspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        Item saveItem = itemDao.save(mapping.toItemEntity(itemDTO));
        if (saveItem == null) {
            throw new DataPersistException("Item not saved");
        }
    }
    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> allItem = itemDao.findAll();
        return mapping.asItemDTOList(allItem);
    }

    @Override
    public ItemStatus getItem(String id) {
        if(itemDao.existsById(id)){
            Item selectedItem =  itemDao.getReferenceById(id);
            return mapping.toItemDTO(selectedItem);
        }else {
            return new SelectedErrorStatus(2, "Item with id " + id + " not found");
        }

    }
    @Override
    public void deleteItem(String id) {
        Optional<Item> existedItem = itemDao.findById(id);
        if(!existedItem.isPresent()){
            throw new ItemNotFoundException("Item with id " + id + " not found");
        }else {
            itemDao.deleteById(id);
        }
    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {
//        Optional<Item> tmpItem = itemDao.findById(id);
//        if(tmpItem.isPresent()) {
//            tmpItem.get().setName(itemDTO.getName());
//            tmpItem.get().setPrice(ItemDTO.getPrice());
//            tmpItem.get().setQty(itemDTO.getQty());
//        }
    }
}
