package com.example.nienluan.models;

import com.example.nienluan.Utils.OrderStatus;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_Order")
  private Integer id;
  @ManyToOne
  @JoinColumn(name = "id_User")
  private User user;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date OrderDate;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date ShipDate;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date DeliveryDate;
  @Column
  private String OrderStatus;
  @OneToOne
  @JoinColumn(name = "id_Payment")
  private Paymentmethod paymentmethod;
  private double totalPrice;

  @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<CartItem> CartItems;
  private String address;
  private String receiver;
  private LocalDateTime createdDate;
  private boolean isPaid;
  private String username;
  private String email;
  private com.example.nienluan.Utils.OrderStatus status;
  private String no;
  private long phoneNumber;

  public String formatVND(long amount) {
    NumberFormat formatter = new DecimalFormat("#,###");
    return formatter.format(amount) + " VNĐ";
  }
}
