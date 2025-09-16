package com.example.looksy.Controller;

import com.example.looksy.repository.ClothesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FilterControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ClothesRepository repository;

  @Test
  public void testClothesFilteredByType() throws Exception {
    //ToDo: Controller Testen
  }
}
