package com.example.nienluan.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="Picture")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Picture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_Picture")
  private Integer id;

  @Column(name = "Caption")
  private String caption;

  @Column(name = "Image_Path")
  private String imagePath;

  @ManyToOne
  @JoinColumn(name = "id_Phone")
  @JsonBackReference
  private Phone phone;
}
