package com.example.demoasm1java5.Repository;

import com.example.demoasm1java5.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Integer orderId);
}
