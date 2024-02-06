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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

     @Column(name = "category_name")
     private String categoryName;

     @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
     private List<Painting> paintingList;

     @CreatedDate
     @Column(name = "created_at", nullable = false, updatable = false)
     private Date createdAt;

     @LastModifiedDate
     @Column(name = "updated_at")
     private LocalDateTime updatedAt;
}
