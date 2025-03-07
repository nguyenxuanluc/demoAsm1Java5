package com.example.demoasm1java5.Repository;

import com.example.demoasm1java5.Entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
}
