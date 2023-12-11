package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private static final int PAGE_SIZE = 8;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Order> getAllOrders(String keyword) {
        List<Order> orders;
        if (keyword != null) {
            Sort sort = Sort.by(Sort.Direction.ASC, "employeeName");
            orders = orderRepository.findByEmployeeNameContainingIgnoreCase(keyword, sort);
        } else {
            orders = orderRepository.findAll();
        }

        for (Order order : orders) {
        	Double  totalPrice = calculateTotalPrice(order);
            order.setTotalPrice(totalPrice);
        }

        return orders;
    }

    @Override
    public Double calculateTotalPrice(Order order) {
        Double totalPrice = orderRepository.findTotalPriceById(order.getId());

        if (totalPrice == null) {
            totalPrice = 0.0; 
        }

        return totalPrice;
    }


    @Override
    public List<Item> getAllItems(String keyword) {
        if (keyword != null) {
            return itemRepository.search(keyword);
        }
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Page<Order> findPaginatedOrder(Integer pageNo, Integer pageSize, String sortField, String sortDirection, String keyword) {
    	Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        if (keyword != null) {
            return orderRepository.findByEmployeeNameContainingIgnoreCase(keyword, pageable);
        } else {
            return orderRepository.findAll(pageable);
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + id));
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    
    @Override
    public List<Order> getInProgressOrders() {
        return orderRepository.getInProgressOrders();
    }
    
    @Override
    public List<Map<String, Object>> getItemSalesDataThisMonth() {
        return orderRepository.getItemSalesData();
    }

    @Override
    public List<Map<String, Object>> getItemSalesDataPreviousMonth() {
        List<Map<String, Object>> result = orderRepository.getItemSalesDataPreviousMonth();
        List<Map<String, Object>> itemSalesDataList = new ArrayList<>();

        for (Map<String, Object> row : result) {
            String itemName = (String) row.get("itemName");
            Number quantitySold = (Number) row.get("quantitySold");

            if (itemName != null && !itemName.isEmpty()) {
                Map<String, Object> itemSalesData = new HashMap<>();
                itemSalesData.put("itemName", itemName);
                itemSalesData.put("quantitySold", quantitySold != null ? quantitySold.intValue() : 0);

                itemSalesDataList.add(itemSalesData);
            }
        }

        return itemSalesDataList;
    }
}
