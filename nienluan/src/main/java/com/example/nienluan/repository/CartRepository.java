package com.example.nienluan.repository;


import com.example.nienluan.models.Cart;
import com.example.nienluan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
  Optional<Cart> findByUser(User user);
}
