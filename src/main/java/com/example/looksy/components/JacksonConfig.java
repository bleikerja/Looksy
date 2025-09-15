package com.example.looksy.components;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class JacksonConfig {
  @Bean
  public SimpleModule colorModule() {
    SimpleModule module = new SimpleModule();
    module.addSerializer(Color.class, new ColorSerializer());
    module.addDeserializer(Color.class, new ColorDeserializer());
    return module;
  }
}
