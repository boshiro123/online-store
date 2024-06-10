package com.example.demo.service.impl;

import com.example.demo.Hash.SHA256;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.exception.*;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends SHA256 {
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    @Autowired
    public UserService(UserRepository userRepository, StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    public boolean save(UserDto userDto) throws NoSuchAlgorithmException {
        try {
            UserDto protect = UserDto.valueOf(userRepository.findUserByName(userDto.getName()));
        }catch (NullPointerException e){
            System.out.println("Пользователь не найдем");
            Status status = statusRepository.findById(1L).orElseThrow(() -> new StatusNotFoundException(1L));
            status.setStatus_name("client");
            status.setId(1L);
            User user = userDto.mapToUser();
            user.setPassword(toHexString(getSHA(user.getPassword())));
            user.setStatus(status);
            user.setCity("");
            user.setAdress("");
            user.setPhone("");
            userRepository.save(user);
            return true;
        }
        userRepository.save(userDto.mapToUser());
        return false;
    }
    public User findUserByName(String name){
        List<User> list = userRepository.findAll();
        User user = new User();
        for(User i: list){
            if(i.getName().equals(name)){
                user=i;
                break;
            }
        }
        return user;
    }
    public UserDto logIn(String name, String password) throws NoSuchAlgorithmException {
        UserDto userDto = new UserDto();
        try {
            userDto = UserDto.valueOf(userRepository.findUserByName(name));
        }catch (NullPointerException e){
            System.out.println("User is not Found");
            return null;
        }
        String checkPassHash = toHexString(getSHA(password));
        if (userDto.getPassword().equals(checkPassHash)){
            userDto.setPassword(password);
//            System.out.println(userDto);
            return userDto;
        }
        return null;
    }

    public UserDto fingById(Long id){
        UserDto userDto = userRepository.findById(id)
                .map(it -> UserDto.valueOf(it))
                .orElseThrow(() -> new ProductNotFoundException(id));

        return userDto;
    }
    public List<UserDto> getUsers(){
        List<UserDto> list = UserDto.ListValueOf(userRepository.findAll());
        return list;
    }

    public void updateUser(Long id, User user) throws NoSuchAlgorithmException {
         User currentUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setPassword(toHexString(getSHA(user.getPassword())));
        userRepository.save(user);
    }

    public void becomeSeller(Long id){
        userRepository.becomeSeller(id);
    }

    public void Block(UserDto user){
        Long id = user.getId();
        User currentUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        System.out.println("id: "+id);
        if(!user.getStatus().getStatus_name().equals("block")){
            user.setStatus(new Status(3L,"block"));

        } else if (user.getStatus().getStatus_name().equals("block")) {
            user.setStatus(new Status(1L,"client"));
        }
        User update = user.mapToUser();
        userRepository.save(update);
    }
}
