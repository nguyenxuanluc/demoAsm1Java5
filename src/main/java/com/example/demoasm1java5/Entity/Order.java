package com.example.demoasm1java5.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double totalPrice;
    private String status = "Pending";

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
}

