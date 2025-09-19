package com.example.looksy.controller;

import com.example.looksy.components.*;
import com.example.looksy.repository.ClothesRepository;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/filter")
public class FilterController {

  private final ClothesRepository repository;

  public FilterController(ClothesRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/type/{type}")
  public List<Clothes> getClothesByType(@PathVariable Type type) {
    List<Clothes> list = repository.findAll();
    Filter filter = new Filter();
    return filter.byType(type, list);
  }

  @GetMapping("/season/{season}")
  public List<Clothes> getClothesBySaison(@PathVariable Season season) {
    List<Clothes> list = repository.findAll();
    Filter filter = new Filter();
    return filter.bySeason(season, list);
  }

  @GetMapping("/size/{size}")
  public List<Clothes> getClothesBySize(@PathVariable Size size) {
    List<Clothes> list = repository.findAll();
    Filter filter = new Filter();
    return filter.bySize(size, list);
  }

  @GetMapping("/material/{material}")
  public List<Clothes> getClothesByMaterial(@PathVariable Material material) {
    List<Clothes> list = repository.findAll();
    Filter filter = new Filter();
    return filter.byMaterial(material, list);
  }

  @GetMapping("/clean/{clean}")
  public List<Clothes> getClothesByCleanliness(@PathVariable Boolean clean) {
    List<Clothes> list = repository.findAll();
    Filter filter = new Filter();
    return filter.byCleanliness(clean, list);
  }

  @GetMapping("color/{Color}")
  public List<Clothes> getClothesByColor(@PathVariable Color Color) {
    List<Clothes> list = repository.findAll();
    Filter filter = new Filter();
    return filter.byColor(Color, list);
  }
}
