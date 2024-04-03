package com.example.nienluan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePhoneDTO {
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
