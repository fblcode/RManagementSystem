package com.example.demo.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.itemName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(i.itemDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Item> search(String keyword);

    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.itemName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(i.itemDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Item> search(String keyword, Pageable pageable);
}
