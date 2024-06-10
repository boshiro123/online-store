package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @Column(name = "city")
    private String city;
    @Column(name = "adress")
    private String adress;
    @Column(name = "phone")
    private String phone;

    public User(Long id, String name, String email, String password, Status status, String city, String adress, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.city = city;
        this.adress = adress;
        this.phone = phone;
    }

    public User(String name, String email, String password, Status status, String city, String adress, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.city = city;
        this.adress = adress;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
