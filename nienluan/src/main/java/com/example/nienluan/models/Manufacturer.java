package com.example.nienluan.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Manufacturer")
@Builder
public class Manufacturer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_Manufacturer", nullable = false)
  private Integer id;


  @Column(name = "name")
  private String name;

//  @Column(name = "id_phone")
//  @OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//  @JsonManagedReference
//  @ToString.Exclude
//  private List<Phone> phones;

}
