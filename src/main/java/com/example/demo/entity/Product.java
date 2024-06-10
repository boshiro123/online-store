package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "likes")
    private int likes;

    @Column(name = "user_creater_id")
    private Long user_creater_id;

    @Column(name = "sales")
    private Long sales;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "prod", cascade = CascadeType.ALL)
    private List<Likes> like;

    @JsonIgnore
    @OneToMany(mappedBy = "produc", cascade = CascadeType.ALL)
    private List<History> histories;

    public Product(Long id, String title, BigDecimal price, Category category, int likes, Long user_creater_id, Long sales) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.likes = likes;
        this.user_creater_id = user_creater_id;
        this.sales = sales;
    }

    public Product(String title, BigDecimal price, Category category, int likes, Long user_creater_id, Long sales) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.likes = likes;
        this.user_creater_id = user_creater_id;
        this.sales = sales;
    }
}
