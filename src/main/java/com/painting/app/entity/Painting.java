package com.painting.app.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paint")
public class Painting {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

     @Column(name = "title")
     private String title;

     @Column(name = "description")
     private String description;

     @Column(name = "published")
     private boolean published;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "author_id")
     private Author author;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "category_id")
     private Category category;

     @CreatedDate
     @Column(name = "created_at", nullable = false, updatable = false)
     private Date createdAt;

     @LastModifiedDate
     @Column(name = "updated_at")
     private LocalDateTime updatedAt;

     public Painting() {
     }

     public Painting(String title, String description, Boolean isPublished) {
          this.title = title;
          this.description = description;
          this.published = isPublished;
     }

     public long getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public Category getCategory() {
          return category;
     }

     public void setCategory(Category category) {
          this.category = category;
     }

     public String getDescription() {
          return description;
     }

     public void setDescription(String description) {
          this.description = description;
     }

     public Author getAuthor() {
          return author;
     }

     public void setOwner(Author author) {
          this.author = author;
     }

     @Override
     public String toString() {
          return "Blog{" + "id=" + id + ", title='" + title + '\'' + ", category='" +
                    category + '\'' +
                    ", description='" + description + '\'' + '}';
     }
}
