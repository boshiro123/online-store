package com.example.demo.controller;

import com.example.demo.dto.HistoryDto;
import com.example.demo.dto.LikesDto;
import com.example.demo.service.impl.HistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {
    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<HistoryDto> findAllByUser_id(@PathVariable("id") Long id) {
        return historyService.findByUserId(id);
    }
}
