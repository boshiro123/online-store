package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDto;
import com.example.demo.repository.CategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CategoryServiceImpl {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll() {
        List<CategoryDto> list = categoryRepository.findAll()
                .stream()
                .map(it -> CategoryDto.valueOf(it))
                .collect(Collectors.toList());

        log.debug("Список всех категорий: " + list);
        return list;
    }
}
