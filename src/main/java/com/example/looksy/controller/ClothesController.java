package com.example.looksy.controller;

import com.example.looksy.Clothes;
import com.example.looksy.repository.ClothesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clothes")
public class ClothesController {
  private final ClothesRepository repository;

  public ClothesController(ClothesRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  public Clothes createClothes(@RequestBody Clothes clothes) {
    return repository.save(clothes);
  }

  @GetMapping
  public List<Clothes> getAllClothes() {
    return repository.findAll();
  }
}
