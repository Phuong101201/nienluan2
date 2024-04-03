package com.example.nienluan.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneRequest {
  private String name;

  private String chip;

  private int ram;

  private int rom;

  private double size;
  private int pin;
  private int quantity;
  private List<CreatePicture> picture;

  private Integer manufacturer;

  private Integer category;

  private  String description;
  private Long price;
}
