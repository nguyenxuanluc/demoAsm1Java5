package com.example.demoasm1java5.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Users")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullName;
    private String email;
    private String phone;
    private String address;

    @Column(nullable = false)
    private String role = "USER";

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();


}
