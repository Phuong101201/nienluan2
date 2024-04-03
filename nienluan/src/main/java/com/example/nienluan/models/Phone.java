package com.example.nienluan.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_Phone", nullable = false)
  private Integer id;

  @Column(name = "name", columnDefinition = "nvarchar(50)")
  private String name;

  @Column(name = "chip")
  private String chip;

  @Column(name = "ram")
  private int ram;

  @Column(name = "pin")
  private int pin;
  @Column(name = "rom")
  private int rom;

  @Column(name = "size")
  private double size;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "description", columnDefinition = "nvarchar(max)")
  private String description;
  @ManyToOne
  @JoinColumn(name = "id_Manufacturer")
  @JsonManagedReference
  private Manufacturer manufacturer;

  @OneToMany(mappedBy = "phone",fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Picture> pictures;

  @OneToOne
  @JoinColumn(name = "id_Category")
  private Category category;
  @Column(name = "Price")
  private Long price;
}
