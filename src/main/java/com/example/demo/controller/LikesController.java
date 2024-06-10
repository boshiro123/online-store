package com.example.demo.controller;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.LikesDto;
import com.example.demo.service.impl.LikesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
public class LikesController {
    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@PathVariable("id")Long id, @RequestBody Long user_id) {
        likesService.add(id,user_id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить корзину со всеми товарами")
    public List<LikesDto> find(@PathVariable("id") Long id) {
        return likesService.findByUserId(id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Удалить товар")
    public void delete(@PathVariable("id") Long id) {
        System.out.println(id);
        likesService.delete(id);
    }
}
