package com.example.looksy.services;

import com.example.looksy.components.Clothes;
import com.example.looksy.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesService {
  @Autowired
  private ClothesRepository clothesRepository;
  public List<Clothes> getAllClothes() {
    return clothesRepository.findAll();
  }
  public Clothes findClothesById(long id) {
    return clothesRepository.findById(id).orElse(null);
  }
  public Clothes saveClothes(Clothes clothes) {
    return clothesRepository.save(clothes);
  }
  public void deleteClothes(long id) {
    clothesRepository.deleteById(id);
  }
}
