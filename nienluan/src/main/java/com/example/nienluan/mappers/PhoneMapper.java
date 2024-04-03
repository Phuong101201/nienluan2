package com.example.nienluan.mappers;

import com.example.nienluan.dto.PhoneRequest;
import com.example.nienluan.dto.PhoneResponse;
import com.example.nienluan.models.Phone;
import com.example.nienluan.models.User;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {

  public Phone toPhone(PhoneRequest phoneRequest){
    Phone phone = Phone.builder()
            .chip(phoneRequest.getChip())
            .name(phoneRequest.getName())
            .price(phoneRequest.getPrice())
            .ram(phoneRequest.getRam())
            .rom(phoneRequest.getRom())
            .size(phoneRequest.getSize())
            .quantity(phoneRequest.getQuantity())
            .description(phoneRequest.getDescription())
            .pin(phoneRequest.getPin())
            .build();
    return phone;
  }

  public PhoneResponse toPhoneResponse(Integer phone, String name){
    PhoneResponse phoneResponse = PhoneResponse.builder()
            .id(phone)
            .name(name)
            .build();
    return phoneResponse;
  }
}
