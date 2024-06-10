package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final MainController mainController;

    @Autowired
    public UserController(UserService userService, MainController mainController) {
        this.userService = userService;
        this.mainController = mainController;
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public boolean registerNewUser(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        return userService.save(userDto);
    }
    @GetMapping("/logIn")
    @ResponseStatus(HttpStatus.OK)
    public UserDto logIn(@RequestParam("name") String email,
                         @RequestParam("password") String password) throws NoSuchAlgorithmException {
        System.out.println(email);
        System.out.println(password);;
        return userService.logIn(email, password);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") Long id, @RequestBody @Valid User user) throws NoSuchAlgorithmException {
        userService.updateUser(id, user);
    }
    @PutMapping("/seller")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody @Valid Long id){
        userService.becomeSeller(id);
    }

    @PutMapping("/block")
    @ResponseStatus(HttpStatus.OK)
    public void Block(@RequestBody @Valid UserDto user) {
        userService.Block(user);
    }

}
