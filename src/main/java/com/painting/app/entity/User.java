package com.painting.app.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "order")
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

     @Column(name = "user_name")
     private String userName;

     @Column(name = "email", unique = true)
     private String email;

     @Column(name = "phone", unique = true)
     private String phone;

     @Column(name = "address")
     private long address;

     @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
     private List<Order> orderList;

     @CreatedDate
     @Column(name = "created_at", nullable = false, updatable = false)
     private Date createdAt;

     @LastModifiedDate
     @Column(name = "updated_at")
     private LocalDateTime updatedAt;
}
