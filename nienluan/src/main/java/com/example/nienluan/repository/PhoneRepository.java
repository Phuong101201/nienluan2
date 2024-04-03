package com.example.nienluan.repository;

import com.example.nienluan.models.Manufacturer;
import com.example.nienluan.models.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Integer>, PagingAndSortingRepository<Phone, Integer>, JpaSpecificationExecutor<Phone> {

  Optional<Phone> findByName(String name);

  Page<Phone> findAllByManufacturer(Manufacturer id, PageRequest page);

//  Page<Phone> findAllByManufacturerId(@Param("manufacturerId") Integer manufacturerId, PageRequest pageRequest);
}
