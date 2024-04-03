package com.example.nienluan.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="CartItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column
  private int quantity;
  @Column(name = "totalPrice")
  private Long totalPrice;

  @ManyToOne
  @JoinColumn(name = "id_Cart")
  @JsonBackReference
  private Cart cart;


  @OneToOne
  @JoinColumn(name = "id_Phone")
  private Phone phone;
}
