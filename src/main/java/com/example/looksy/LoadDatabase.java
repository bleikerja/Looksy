package com.example.looksy;

import com.example.looksy.components.*;
import com.example.looksy.repository.ClothesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


  @Bean
  CommandLineRunner initDatabase(ClothesRepository repository) {
    return args -> {
      log.info("Preloading Clothes " + repository.save(new Clothes(Color.black, Size._38, Season.inBetween, Type.Pants, Material.jeans, true)));
      log.info("Preloading Clothes " + repository.save(new Clothes(Color.cyan, Size._40, Season.Summer, Type.Tops, Material.Cotton, false)));
    };
  }
}
