package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product produc;

    @Column(name = "count")
    private int count;

    @Column(name = "date")
    private LocalDate date;

    public History(Long user_id, Product product, int count, LocalDate date) {
        this.user_id = user_id;
        this.produc = product;
        this.count = count;
        this.date = date;
    }
}
