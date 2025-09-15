package com.example.looksy.controller;

import com.example.looksy.components.Clothes;
import com.example.looksy.repository.ClothesRepository;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Clothes> createClothes(@RequestBody Clothes clothes) {
    return ResponseEntity.ok(clothes);
  }

  @GetMapping
  public List<Clothes> getAllClothes() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Clothes findClothes(@PathVariable long id) {
    return repository.findById(id).orElse(null);
  }

  @DeleteMapping("/{id}")
  public void deleteClothes(@PathVariable long id) {
    repository.deleteById(id);
  }
}
