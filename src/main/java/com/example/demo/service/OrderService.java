package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.model.Order;

import java.util.List;

import org.springframework.data.domain.Page;

public interface OrderService {
	
    void saveOrder(Order order);
    
    void deleteOrder(Long id);
    
    Order getOrderById(Long id);
    
    List<Order> getAllOrders(String keyword);
    
    List<Item> getAllItems();
    
    Page<Order> findPaginatedOrder(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String keyword);

	List<Item> getAllItems(String keyword);
	
	Double calculateTotalPrice(Order order);
}