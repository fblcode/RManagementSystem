package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.AuthenticationFacade;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/order")
    public String viewOrderHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Order> orders = orderService.getAllOrders(keyword);

        model.addAttribute("listOrders", orders);
        model.addAttribute("keyword", keyword);

        return findPaginatedOrder(1, "id", "asc", keyword, model);
    }





    @GetMapping("/order/showNewOrderForm")
    public String showNewOrderForm(Model model) {
        List<Item> items = itemService.getAllItems();
        Order order = new Order();
        Map<String, Integer> quantityMap = new HashMap<>();
        for (Item item : items) {
            quantityMap.put(String.valueOf(item.getItemID()), 1);
        }
        order.setQuantityMap(quantityMap);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "new_order";
    }

    @PostMapping("/order/saveOrder")
    public String saveOrder(@ModelAttribute("order") Order order) {
        // Set the employee name based on the authenticated user's email
        String employeeEmail = authenticationFacade.getAuthentication().getName();
        order.setEmployeeName(employeeEmail);

        // Calculate the total price based on the selected items and quantities
        Double totalPrice = 0.0;
        for (Map.Entry<String, Integer> entry : order.getQuantityMap().entrySet()) {
            String itemId = entry.getKey();
            int quantity = entry.getValue();
            Item item = itemService.getItemById(Integer.parseInt(itemId));
            totalPrice += item.getItemPrice() * quantity;
        }
        order.setTotalPrice(totalPrice);

        // Save the order to the database
        orderService.saveOrder(order);
        return "redirect:/order";
    }




    @GetMapping("/order/showFormForUpdateOrder/{id}")
    public String showFormForUpdateOrder(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderService.getOrderById(id);
        List<Item> items = itemService.getAllItems();
        
        // Populate the quantity map with the existing quantities from the order
        Map<String, Integer> quantityMap = new HashMap<>();
        for (Item item : items) {
            String itemId = String.valueOf(item.getItemID());
            int quantity = order.getQuantityMap().getOrDefault(itemId, 1);
            quantityMap.put(itemId, quantity);
        }
        order.setQuantityMap(quantityMap);
        
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "update_order";
    }


    @GetMapping("/order/deleteOrder/{id}")
    public String deleteOrder(@PathVariable(value = "id") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/order";
    }


    @GetMapping("/order/page/{pageNo}")
    public String findPaginatedOrder(
            @PathVariable(value = "pageNo") Integer pageNo,
            @RequestParam(value = "sortField", required = false, defaultValue = "itemName") String sortField,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        int pageSize = 8;

        Page<Order> page = orderService.findPaginatedOrder(pageNo, pageSize, sortField, sortDir, keyword);
        List<Order> listOrders = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listOrders", listOrders);
        return "order";
    }
}