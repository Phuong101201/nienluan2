package com.example.nienluan.dto;

import com.example.nienluan.models.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePicture {
  private String caption;
  private String imagePath;
}
