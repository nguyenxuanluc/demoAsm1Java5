package com.example.demoasm1java5.Repository;

import com.example.demoasm1java5.Entity.Cart;
import com.example.demoasm1java5.Entity.Product;
import com.example.demoasm1java5.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(User user);
    void deleteByUserId(Integer userId);
    Product findProductById(Long productId);
    List<Cart> findAll();
    Cart findByProduct(Product product);
    Optional<Cart> findByUserAndProduct(User user, Product product);
}
