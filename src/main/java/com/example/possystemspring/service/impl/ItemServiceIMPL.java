package com.example.possystemspring.service.impl;

import com.example.possystemspring.customStatusCodes.SelectedErrorStatus;
import com.example.possystemspring.dao.ItemDao;
import com.example.possystemspring.dto.ItemStatus;
import com.example.possystemspring.dto.impl.ItemDTO;
import com.example.possystemspring.entity.impl.Item;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.exception.ItemNotFoundException;
import com.example.possystemspring.service.ItemService;
import com.example.possystemspring.util.AppUtil;
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
    public void save(ItemDTO itemDTO) {
        itemDTO.setId(AppUtil.generateItemId());
        Item saveitem = itemDao.save(mapping.toItemEntity(itemDTO));
        if (saveitem == null){
            throw new DataPersistException("Item not saved");
        }
    }
    @Override
    public List<ItemDTO> getAll() {
        return mapping.asItemDTOList(itemDao.findAll());
    }
    @Override
    public ItemDTO get(String id) {
        if (itemDao.existsById(id)) {
            Item item = itemDao.getReferenceById(id);
            return mapping.toItemDTO(item);
        }else {
            throw new DataPersistException("Item not found");
        }
    }
    @Override
    public void delete(String id) {
        if (!itemDao.existsById(id)){
            throw new DataPersistException("Item not found");
        }else {
            itemDao.deleteById(id);
        }
    }

    @Override
    public void update(String id, ItemDTO dto) {
        Optional<Item> item = itemDao.findById(id);
        if (item == null){
            throw new DataPersistException("Item not found");
        }else {
            item.get().setName(dto.getName());
            item.get().setQty(dto.getQty());
            item.get().setPrice(dto.getPrice());
        }
    }
}
