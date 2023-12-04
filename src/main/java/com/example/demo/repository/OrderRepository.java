package com.example.demo.repository;

import com.example.demo.model.Order;

import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("SELECT o.totalPrice FROM Order o WHERE o.id = :orderId") // Fetch totalPrice directly
	Double findTotalPriceById(@Param("orderId") Long orderId); // Use BigDecimal for totalPrice

    List<Order> findByEmployeeNameContainingIgnoreCase(String keyword, Sort sort);

    Page<Order> findByEmployeeNameContainingIgnoreCase(String keyword, Pageable pageable);

    // You can add more custom query methods here
    
    @Query("SELECT o FROM Order o WHERE o.employeeName LIKE %:keyword%")
    List<Order> searchByEmployeeName(@Param("keyword") String keyword, Sort sort);

    @Query("SELECT o FROM Order o WHERE o.employeeName LIKE %:keyword%")
    Page<Order> searchByEmployeeName(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o")
    Double sumTotalPrice();
    
    @Query(value = "SELECT i.item_name AS itemName, COALESCE(SUM(oq.quantity), 0) AS quantitySold " +
            "FROM items i " +
            "LEFT JOIN order_quantity oq ON i.itemID = oq.item_id " +
            "GROUP BY i.item_name", nativeQuery = true)
    List<Map<String, Object>> getItemSalesData();
    
    List<Order> findByEmployeeName(String employeeName);
}