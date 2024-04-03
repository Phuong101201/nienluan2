package com.example.nienluan.services;

import com.example.nienluan.dto.PhoneRequest;
import com.example.nienluan.dto.PhoneResponse;
import com.example.nienluan.models.Phone;
import org.springframework.data.domain.Page;

public interface PhoneService {
  Page<Phone> getPhones(int page, int size);
  PhoneResponse addPhone(PhoneRequest phoneRequest);

  Page<Phone> getListPhoneByManufacturer(Integer id, int page, int size);
  public void deletePhone(int id);
  Phone getPhone(int id);
}
