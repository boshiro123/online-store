package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="status_name")
    private String status_name;
    @JsonIgnore
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<User> users;

    public Status(Long id, String status_name) {
        this.id = id;
        this.status_name = status_name;
    }
}
