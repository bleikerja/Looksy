package com.example.looksy.repository;

import com.example.looksy.components.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository <Clothes, Long> {
}
