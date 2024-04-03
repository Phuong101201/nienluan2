package com.example.nienluan.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_Cart")
  private Integer id;

  @OneToOne
  @JoinColumn(name = "id_User")
  @JsonManagedReference
  @ToString.Exclude
  private User user;

  @Column
  private int totalItems;

  @Column
  private Long totalPrices;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
  @JsonManagedReference
  @ToString.Exclude
  private Set<CartItem> cartItem;
}
