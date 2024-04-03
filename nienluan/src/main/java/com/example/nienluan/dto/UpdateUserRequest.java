package com.example.nienluan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {
  private String name;
  private String phoneNumber;
  private int age;
  private String address;
}
