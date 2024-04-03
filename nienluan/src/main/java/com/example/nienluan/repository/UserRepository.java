package com.example.nienluan.repository;

import com.example.nienluan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
  Optional<User> findByUsername(String username);
  Optional<User> findById(Integer id);
}
