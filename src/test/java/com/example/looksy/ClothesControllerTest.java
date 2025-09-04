package com.example.looksy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClothesControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testCreateClothes() throws Exception {
    String json = """
      {
        "color": "Blau",
        "size": "M",
        "seasonUsage": "Winter",
        "type": "Jacke",
        "material": "Wolle",
        "clean": true
      }
    """;

    mockMvc.perform(post("/api/clothes")
            .contentType(String.valueOf(MediaType.APPLICATION_JSON))
            .content(json))
        .andExpect(status().isOk());
  }
}
