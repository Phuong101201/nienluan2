package com.example.nienluan.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponse {
  @Column(name = "name")
  private String name;

  @Column(name = "Age")
  private int age;

  @Column(name = "Sex")
  private Boolean sex;
  private String phoneNumber;
  private String address;

  @Column(name = "Year_Of_Birth")
  private int DOB;
}
