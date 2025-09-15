package com.example.looksy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(title = "Looksy API", version = "v1", description = "CRUD für Clothes")
)
@SpringBootApplication
public class LooksyApplication {

  public static void main(String[] args) {
    SpringApplication.run(LooksyApplication.class, args);
  }

}
