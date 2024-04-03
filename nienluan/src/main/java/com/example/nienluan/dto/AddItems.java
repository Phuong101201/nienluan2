package com.example.nienluan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddItems {

  private String username;
  private String name;
  private String image;
  private Long price;
}
