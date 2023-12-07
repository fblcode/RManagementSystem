package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_quantity", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "item_id")
    @Column(name = "quantity")
    private Map<String, Integer> quantityMap = new HashMap<>();

    @Column(name = "total_price")
    private Double  totalPrice;

    public Double  getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double  totalPrice) {
        this.totalPrice = totalPrice;
    }

	@Column(name = "employee_name")
    private String employeeName;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;
	
	public enum OrderStatus {
	    IN_PROGRESS,
	    COMPLETED
	}
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    // Constructors, getters, and setters

    public Map<String, Integer> getQuantityMap() {
  		return quantityMap;
  	}

  	public void setQuantityMap(Map<String, Integer> quantityMap) {
  		this.quantityMap = quantityMap;
  	}



    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
    
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
}
