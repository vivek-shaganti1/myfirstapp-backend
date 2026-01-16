
package com.example.demo.order;

import com.example.demo.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔐 Who placed the order
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 💰 Total price snapshot
    private double totalAmount;

    // 📦 Order status
    private String status; // PLACED, PAID, SHIPPED, CANCELLED

    // ⏰ When order was placed
    private LocalDateTime createdAt;

    // 📄 Order items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference  
    private List<OrderItem> items;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = "PLACED";
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}