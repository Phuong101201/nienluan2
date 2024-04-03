package com.example.nienluan.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_order_detail")
  private Integer id;
  @Column(name="quantity")
  private int quantity;
  @Column(name="totalPrice")
  private long totalPrice;
  @Column(name="unitPrice")
  private long unitPrice;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_Order")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "id_Phone")
  private Phone phone;
  public String formatVND(long amount) {
    NumberFormat formatter = new DecimalFormat("#,###");
    return formatter.format(amount) + " VNĐ";
  }
}
