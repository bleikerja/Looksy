package com.example.looksy.Controller;

import com.example.looksy.components.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class FilterControllerTest {

  private final MockMvcTester mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  FilterControllerTest(@Autowired WebApplicationContext wac) {
    this.mockMvc = MockMvcTester.from(wac);
  }

  @Test
  public void testClothesFilteredByType() throws Exception {
    MvcTestResult response = mockMvc.get().uri("/api/filter/type/Pants").exchange();

    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Clothes.class);
    List<Clothes> filteredList = objectMapper.readValue(response.getResponse().getContentAsString(), collectionType);
    Clothes actualElement = filteredList.getFirst();

    assertThat(response.getResponse().getStatus()).isEqualTo(200);
    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement.getType()).isEqualTo(Type.Pants);
  }

  @Test
  public void testClothesFilteredBySeason() throws Exception {
    MvcTestResult response = mockMvc.get().uri("/api/filter/season/Summer").exchange();

    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Clothes.class);
    List<Clothes> filteredList = objectMapper.readValue(response.getResponse().getContentAsString(), collectionType);
    Clothes actualElement = filteredList.getFirst();

    assertThat(response.getResponse().getStatus()).isEqualTo(200);
    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement.getSeasonUsage()).isEqualTo(Season.Summer);
  }

  @Test
  public void testClothesFilteredBySize() throws Exception {
    MvcTestResult response = mockMvc.get().uri("/api/filter/size/_40").exchange();

    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Clothes.class);
    List<Clothes> filteredList = objectMapper.readValue(response.getResponse().getContentAsString(), collectionType);
    Clothes actualElement = filteredList.getFirst();

    assertThat(response.getResponse().getStatus()).isEqualTo(200);
    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement.getSize()).isEqualTo(Size._40);
  }

  @Test
  public void testClothesFilteredByMaterial() throws Exception {
    MvcTestResult response = mockMvc.get().uri("/api/filter/material/jeans").exchange();

    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Clothes.class);
    List<Clothes> filteredList = objectMapper.readValue(response.getResponse().getContentAsString(), collectionType);
    Clothes actualElement = filteredList.getFirst();

    assertThat(response.getResponse().getStatus()).isEqualTo(200);
    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement.getMaterial()).isEqualTo(Material.jeans);
  }

  @Test
  public void testClothesFilteredByCleanliness() throws Exception {
    MvcTestResult response = mockMvc.get().uri("/api/filter/clean/true").exchange();

    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Clothes.class);
    List<Clothes> filteredList = objectMapper.readValue(response.getResponse().getContentAsString(), collectionType);
    Clothes actualElement = filteredList.getFirst();

    assertThat(response.getResponse().getStatus()).isEqualTo(200);
    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement.getClean()).isEqualTo(true);
  }

  @Test
  public void testClothesFilteredByColor() throws Exception {
    MvcTestResult response = mockMvc.get().uri("/api/filter/color/cyan").exchange();

    CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Clothes.class);
    List<Clothes> filteredList = objectMapper.readValue(response.getResponse().getContentAsString(), collectionType);
    Clothes actualElement = filteredList.getFirst();

    assertThat(response.getResponse().getStatus()).isEqualTo(200);
    assertThat(filteredList.size()).isEqualTo(1);
    assertThat(actualElement.getColor()).isEqualTo(Color.cyan);
  }
}
