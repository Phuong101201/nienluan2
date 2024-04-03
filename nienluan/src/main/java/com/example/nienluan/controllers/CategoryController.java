package com.example.nienluan.controllers;

import com.example.nienluan.dto.CategoryRequest;
import com.example.nienluan.dto.ManufacturerRequest;
import com.example.nienluan.models.Category;
import com.example.nienluan.models.Manufacturer;
import com.example.nienluan.repository.CategoryRepository;
import com.example.nienluan.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private ManufacturerRepository manufacturerRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @PostMapping("/addmanufacturer")
  public void createManufacturer(@RequestBody ManufacturerRequest name){
//    manufacturerRepository.findAll().forEach(manufacturer -> System.out.println(manufacturer.getPhones().get(0).getChip()));
    Manufacturer manufacturer = Manufacturer.builder().name(name.getName()).build();
     manufacturerRepository.save(manufacturer);
  }
  @PostMapping("/addcategory")
  public void createCategory(@RequestBody CategoryRequest name){
//    manufacturerRepository.findAll().forEach(manufacturer -> System.out.println(manufacturer.getPhones().get(0).getChip()));
    Category manufacturer = Category.builder().name(name.getName()).build();
    categoryRepository.save(manufacturer);
  }
  @GetMapping("/manufacturer")
  public List<Manufacturer> getAllManufacturer(){
//    manufacturerRepository.findAll().forEach(manufacturer -> System.out.println(manufacturer.getPhones().get(0).getChip()));
    return manufacturerRepository.findAll();
  }
  @GetMapping("/all")
  public List<Category> getAll(){
//    manufacturerRepository.findAll().forEach(manufacturer -> System.out.println(manufacturer.getPhones().get(0).getChip()));
    return categoryRepository.findAll();
  }
}
