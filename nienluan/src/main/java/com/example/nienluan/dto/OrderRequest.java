package com.example.nienluan.dto;

import com.example.nienluan.models.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
  private String username;
  private String name;
  private Set<CartItem> items;
  private String address;
  private Integer paymentMethod;
  private long phoneNumber;
  private String email;
  private boolean paid;
}
