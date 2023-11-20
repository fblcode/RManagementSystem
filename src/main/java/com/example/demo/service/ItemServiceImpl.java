package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List < Item > getAllItems(String keyword) {
    	if (keyword != null) {
            return itemRepository.search(keyword);
        }
        return itemRepository.findAll();
    }
    
    @Override
    public List < Item > getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void saveItem(Item item) {
        this.itemRepository.save(item);
    }

    @Override
    public Item getItemById(Integer itemID) {
        Optional < Item > optional = itemRepository.findById(itemID);
        Item item = null;
        if (optional.isPresent()) {
        	item = optional.get();
        } else {
            throw new RuntimeException(" Item not found for id :: " + itemID);
        }
        return item;
    }

    @Override
    public void deleteItemById(Integer itemID) {
        this.itemRepository.deleteById(itemID);
    }

    @Override
    public Page<Item> findPaginatedItem(int pageNo, int pageSize, String sortField, String sortDirection,
            String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        if (keyword != null) {
            return itemRepository.search(keyword, pageable);
        }
        return itemRepository.findAll(pageable);
    }
}
