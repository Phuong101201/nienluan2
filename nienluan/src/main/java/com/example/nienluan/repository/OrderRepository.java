package com.example.nienluan.repository;


import com.example.nienluan.Utils.OrderStatus;
import com.example.nienluan.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Page<Order> findAll(Pageable pageable);

  Optional<Order> findByNo(String no);

  Optional<Order> findByUsername(String username);

  List<Order> findAllByStatus(OrderStatus cancel);

  Optional<List<Order>> findAllByUsername(String user);

  List<Order> findAllByStatusAndIsPaid(OrderStatus status, boolean paid);
}
