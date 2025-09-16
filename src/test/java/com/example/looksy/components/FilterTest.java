package com.example.looksy.components;

import com.example.looksy.repository.ClothesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class FilterTest {
  @Autowired
  private ClothesRepository repository;

  @Test
  public void testSortByType() {
    List<Clothes> list = repository.findAll();
    Filter filter = new Filter();
    Clothes oneListElement =  filter.byType(Type.Pants, list).getFirst();
    assertThat(oneListElement).isInstanceOf(Clothes.class);
    assertThat(oneListElement.getType()).isEqualTo(Type.Pants);
    assertThat(filter.byType(Type.Pants, list).size()).isEqualTo(1);
  }
}
