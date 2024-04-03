package com.example.nienluan.configs;

import com.example.nienluan.models.Phone;
import org.springframework.data.jpa.domain.Specification;

public class PhoneSpecification {
  public static Specification<Phone> hasName(String name) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name);
  }
}
