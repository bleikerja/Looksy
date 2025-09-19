package com.example.looksy.components;

import com.example.looksy.repository.ClothesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class FilterTest {
  @Autowired
  private ClothesRepository repository;
  Filter filter = new Filter();

  @Test
  public void testSortByType() {
    List<Clothes> list = repository.findAll();
    Clothes oneListElement =  filter.byType(Type.Pants, list).getFirst();
    assertThat(oneListElement).isInstanceOf(Clothes.class);
    assertThat(oneListElement.getType()).isEqualTo(Type.Pants);
    assertThat(filter.byType(Type.Pants, list).size()).isEqualTo(1);
  }

  @Test
  public void testSortBySeason() {
    List<Clothes> list = repository.findAll();
    Clothes expectedElement = list.get(1);

    List<Clothes> filteredList = filter.bySeason(Season.Summer, list);
    Clothes actualElement = filteredList.getFirst();

    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement).isEqualTo(expectedElement);
    assertThat(actualElement.getSeasonUsage()).isEqualTo(Season.Summer);
  }

  @Test
  public void testSortBySize() {
    List<Clothes> list = repository.findAll();
    Clothes expectedElement = list.get(1);

    List<Clothes> filteredList = filter.bySize(Size._40, list);
    Clothes actualElement = filteredList.getFirst();

    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement).isEqualTo(expectedElement);
    assertThat(actualElement.getSize()).isEqualTo(Size._40);
  }

  @Test
  public void testSortByMaterial() {
    List<Clothes> list = repository.findAll();
    Clothes expectedElement = list.getFirst();

    List<Clothes> filteredList = filter.byMaterial(Material.jeans, list);
    Clothes actualElement = filteredList.getFirst();

    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement).isEqualTo(expectedElement);
    assertThat(actualElement.getMaterial()).isEqualTo(Material.jeans);
  }

  @Test
  public void testSortByCleanliness() {
    List<Clothes> list = repository.findAll();
    Clothes expectedElement = list.getFirst();

    List<Clothes> filteredList = filter.byCleanliness(true, list);
    Clothes actualElement = filteredList.getFirst();

    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement).isEqualTo(expectedElement);
    assertThat(actualElement.getClean()).isEqualTo(true);
  }

  @Test
  public void testSortByColor() {
    List<Clothes> list = repository.findAll();
    Clothes expectedElement = list.get(1);

    List<Clothes> filteredList = filter.byColor(Color.cyan, list);
    Clothes actualElement = filteredList.getFirst();

    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement).isEqualTo(expectedElement);
    assertThat(actualElement.getColor()).isEqualTo(Color.cyan);
  }
}
