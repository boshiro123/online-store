package com.example.demo.dto;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Status status;
    private String city;
    private String adress;
    private String phone;


    public static UserDto valueOf(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus(),
                user.getCity(),
                user.getAdress(),
                user.getPhone()
        );
    }
    public static List<UserDto> ListValueOf(List<User> users) {
        List<UserDto> list = new ArrayList<>();
        for(User i: users) {
            list.add(valueOf(i));
        }
        return list;
    }
    public User mapToUser() {
        return new User(id,name,email,password,status,city,adress, phone);
    }


}
