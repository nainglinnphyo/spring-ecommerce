package com.paint.painting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Paint {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Number price;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne()
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "paint", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("paint")
    private List<OrderItem> orderItems;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    public Paint() {

    }

    public Paint(String title,String imagePath, Artist artist,Category category) {
        this.title = title;
        this.imagePath = imagePath;
        this.artist = artist;
        this.category = category;
    }

}
