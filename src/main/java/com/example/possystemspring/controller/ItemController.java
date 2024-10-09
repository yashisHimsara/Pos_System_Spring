package com.example.possystemspring.controller;

import com.example.possystemspring.customStatusCodes.SelectedErrorStatus;
import com.example.possystemspring.dto.ItemStatus;import com.example.possystemspring.dto.impl.ItemDTO;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.service.ItemService;
import com.example.possystemspring.util.AppUtil;
import com.example.possystemspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)

        public ResponseEntity<Void> saveitem(
                @RequestPart("name") String name,
                @RequestPart ("price") double price,
                @RequestPart ("qty") int qty
        ) {
            try {
                //ItemId generate
                String id = AppUtil.generateItemId();
                //Build the Object
                ItemDTO buildItemDTO = new ItemDTO();
                buildItemDTO.setId(id);
                buildItemDTO.setName(name);
                buildItemDTO.setPrice(price);
                buildItemDTO.setQty(qty);
                itemService.saveItem(buildItemDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistException e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ItemStatus getSelectedItem(@PathVariable ("id") String id){
            if(!RegexProcess.itemIdMatcher(id)){
                return new SelectedErrorStatus(1,"Item ID is not valid");
            }
            return itemService.getItem(id);
        }
    }
