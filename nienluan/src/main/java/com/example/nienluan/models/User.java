package com.example.nienluan.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_User")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "Age")
    private int age;

    @Column(name = "Sex")
    private Boolean sex;

    @Column(name = "Year_Of_Birth")
    private int DOB;

    @Column(name = "Username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "Phone")
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="User_Role", joinColumns = @JoinColumn(name="id_User"), inverseJoinColumns = @JoinColumn(name="ID_Role"))
    private List<Role> roles = new ArrayList<>();


  // getters và setters
}
