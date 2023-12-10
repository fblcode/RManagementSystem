package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AuthenticationFacade;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/home")
    public String viewInProgressOrders(Model model) {
        // Retrieve orders with "In Progress" status
        List<Order> inProgressOrders = orderService.getInProgressOrders();

        model.addAttribute("listOrdersInProgress", inProgressOrders);

        // You can add other attributes as needed

        return "home";
    }
    
    @GetMapping("/home/showNewOrderForm")
    public String showNewOrderFormFromHome(Model model, HttpSession session) {
        // Store information in the session about the source of the request
        session.setAttribute("source", "home");
        
        List<Item> items = itemService.getAllItems();
        Order order = new Order();
        Map<String, Integer> quantityMap = new HashMap<>();
        for (Item item : items) {
            quantityMap.put(String.valueOf(item.getItemID()), 0);
        }
        order.setQuantityMap(quantityMap);
        
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        model.addAttribute("statusOptions", Order.OrderStatus.values()); // Add status options to the model
        
        return "new_order";
    }
    
    @GetMapping("/home/showFormForUpdateOrder/{id}")
    public String showFormForUpdateOrderFromHome(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
        // Store information in the session about the source of the request
        session.setAttribute("source", "home");

        // Rest of your existing code to retrieve and populate order details
        Order order = orderService.getOrderById(id);
        List<Item> items = itemService.getAllItems();
        
        // Populate the quantity map with the existing quantities from the order
        Map<String, Integer> quantityMap = new HashMap<>();
        for (Item item : items) {
            String itemId = String.valueOf(item.getItemID());
            int quantity = order.getQuantityMap().getOrDefault(itemId, 0);
            quantityMap.put(itemId, quantity);
        }
        order.setQuantityMap(quantityMap);
        
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        model.addAttribute("statusOptions", Order.OrderStatus.values());

        return "update_order";
    }

}
