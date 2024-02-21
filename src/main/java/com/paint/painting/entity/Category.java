package com.paint.painting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
     @Setter
     @Getter
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "name")
     private String name;

     @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
     @JsonIgnoreProperties("category")
     private List<Paint> paints;

     @CreatedDate
     @Column(name = "created_at", nullable = false, updatable = false)
     private Date createdAt;

     public Category() {
         
     }

     // public Category(String name, List<Paint> paints) {
     //      this.name = name;
     //      this.paints = paints;
     // }

}
