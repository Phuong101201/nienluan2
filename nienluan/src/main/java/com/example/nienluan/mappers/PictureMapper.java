package com.example.nienluan.mappers;

import com.example.nienluan.dto.CreatePicture;
import com.example.nienluan.models.Phone;
import com.example.nienluan.models.Picture;
import org.springframework.stereotype.Component;

@Component
public class PictureMapper {
  public Picture toPicture(CreatePicture picture, Phone phone){
    return Picture.builder()
            .caption(picture.getCaption())
            .imagePath(picture.getImagePath())
            .phone(phone)
            .build();
  }
}
