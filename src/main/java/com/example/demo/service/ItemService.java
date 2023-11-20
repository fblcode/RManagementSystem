package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Item;

public interface ItemService {
	public List<Item> getAllItems(String keyword);
	
	public List<Item> getAllItems();
	
	void saveItem(Item item);
	
	Item getItemById(Integer itemID);
	
	void deleteItemById(Integer itemID);
	
	Page<Item> findPaginatedItem(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
}

