package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AuthenticationFacade;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order")
    public String viewOrderHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Order> orders = orderService.getAllOrders(keyword);

        model.addAttribute("listOrders", orders);
        model.addAttribute("keyword", keyword);

        return findPaginatedOrder(1, "id", "asc", keyword, model);
    }

    @GetMapping("/order/showNewOrderForm")
    public String showNewOrderForm(Model model, HttpSession session) {
    	session.setAttribute("source", "order");
        List<Item> items = itemService.getAllItems();
        Order order = new Order();
        Map<String, Integer> quantityMap = new HashMap<>();
        for (Item item : items) {
            quantityMap.put(String.valueOf(item.getItemID()), 0);
        }
        order.setQuantityMap(quantityMap);
        
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        model.addAttribute("statusOptions", Order.OrderStatus.values());
        
        return "new_order";
    }

    @PostMapping("/order/saveOrder")
    public String saveOrder(@ModelAttribute("order") Order order, @RequestParam("status") String status, HttpSession session) {

        String employeeEmail = authenticationFacade.getAuthentication().getName();
        order.setEmployeeName(employeeEmail);

        Double totalPrice = 0.0;
        for (Map.Entry<String, Integer> entry : order.getQuantityMap().entrySet()) {
            String itemId = entry.getKey();
            int quantity = entry.getValue();
            Item item = itemService.getItemById(Integer.parseInt(itemId));
            order.setOrderDate(new Date());
            if (!status.isEmpty()) {
                order.setStatus(Order.OrderStatus.valueOf(status));
            }
            if (item != null && item.getItemQuantity() != null) {
                Double itemPrice = item.getItemPrice();

                int updatedQuantity = item.getItemQuantity() - quantity;
                if (updatedQuantity >= 0) {
                    item.setItemQuantity(updatedQuantity);
                    itemService.saveItem(item);

                    if (!order.getItems().contains(item)) {
                        order.addItem(item);
                    }

                    totalPrice += itemPrice * quantity;
                } else {

                }
            } else {

            }
        }
        order.setTotalPrice(totalPrice);

        orderService.saveOrder(order);
        String source = (String) session.getAttribute("source");

        if ("home".equals(source)) {
            session.removeAttribute("source");

            return "redirect:/home";
        } else {
            return "redirect:/order";
        }
    }


    @GetMapping("/order/showFormForUpdateOrder/{id}")
    public String showFormForUpdateOrder(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderService.getOrderById(id);
        List<Item> items = itemService.getAllItems();
        
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

    @GetMapping("/order/deleteOrder/{id}")
    public String deleteOrder(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        orderService.deleteOrder(id);
        String referrer = request.getHeader("referer");
        if (referrer != null && referrer.contains("/home")) {
            return "redirect:/home";
        } else {
            return "redirect:/order";
        }
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

    @GetMapping("/analytics")
    public String viewAnalytics(Model model) {
        // Fetch analytics data using the OrderService
        Map<String, Object> analyticsData = new HashMap<>();
        analyticsData.put("totalOrders", orderRepository.count());
        analyticsData.put("totalRevenue", orderRepository.sumTotalPrice());
        analyticsData.put("itemSales", orderService.getItemSalesDataThisMonth());
        analyticsData.put("itemSalesThisMonth", orderService.getItemSalesDataThisMonth()); 
        analyticsData.put("itemSalesPreviousMonth", orderService.getItemSalesDataPreviousMonth()); 

        model.addAttribute("analyticsData", analyticsData);

        return "analytics";
    }

    @GetMapping("/analytics-data")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAnalyticsData(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month) {
        try {
            Map<String, Object> analyticsData = new HashMap<>();
            analyticsData.put("totalOrders", orderRepository.countTotalOrdersForMonth(year, month));
            analyticsData.put("totalRevenue", orderRepository.sumTotalRevenueForMonth(year, month));
            analyticsData.put("itemSalesThisMonth", orderService.getItemSalesDataThisMonth());
            analyticsData.put("itemSalesPreviousMonth", orderService.getItemSalesDataPreviousMonth());

            System.out.println("Analytics Data: " + analyticsData);

            return ResponseEntity.ok(analyticsData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
