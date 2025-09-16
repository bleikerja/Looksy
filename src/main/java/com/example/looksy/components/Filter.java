package com.example.looksy.components;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Filter {
  public List<Clothes> byType(Type type, List<Clothes> list) {
    return list.stream().filter(c -> c.getType() == type).collect(Collectors.toList());
  }
}
