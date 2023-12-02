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

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final int PAGE_SIZE = 8; // Adjust the page size according to your needs

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Order> getAllOrders(String keyword) {
        List<Order> orders;
        // Retrieve orders from the repository
        if (keyword != null) {
            Sort sort = Sort.by(Sort.Direction.ASC, "employeeName");
            orders = orderRepository.findByEmployeeNameContainingIgnoreCase(keyword, sort);
        } else {
            orders = orderRepository.findAll();
        }

        // Calculate total price for each order
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
            // Handle the case where the total price is null
            totalPrice = 0.0; // Set a default value or handle it based on your requirements
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
}
