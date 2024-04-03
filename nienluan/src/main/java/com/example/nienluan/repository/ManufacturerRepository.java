package com.example.nienluan.repository;

import com.example.nienluan.models.Manufacturer;
import com.example.nienluan.models.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>, PagingAndSortingRepository<Manufacturer, Integer> {
//  Optional<Manufacturer> findByName(String name, Pageable pageable);

//  Page<Phone> findById(Integer id, PageRequest pageRequest);
}
