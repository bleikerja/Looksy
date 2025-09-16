package com.example.looksy.controller;

import com.example.looksy.components.Clothes;
import com.example.looksy.components.Filter;
import com.example.looksy.components.Type;
import com.example.looksy.repository.ClothesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filter")
public class FilterController {

  private ClothesRepository repository;

  @GetMapping("/{type}")
  public List<Clothes> getClothesByType(@PathVariable Type type) {
    List<Clothes> list = repository.findAll();
    Filter filter = new Filter();
    return filter.byType(type, list);
  }
}
