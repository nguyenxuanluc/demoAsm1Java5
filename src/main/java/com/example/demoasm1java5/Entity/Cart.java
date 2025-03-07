    package com.example.demoasm1java5.Entity;
    import jakarta.persistence.*;
    import lombok.Data;
    import java.util.Date;

    @Entity
    @Table(name = "Cart")
    @Data
    public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;

        private Integer quantity;

        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt = new Date();
    }


