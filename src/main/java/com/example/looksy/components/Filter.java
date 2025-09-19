package com.example.looksy.components;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Filter {
  public List<Clothes> byType(Type type, List<Clothes> list) {
    return list.stream().filter(c -> c.getType() == type).collect(Collectors.toList());
  }

  public List<Clothes> bySeason(Season season, List<Clothes> list) {
    return list.stream().filter(c -> c.getSeasonUsage() == season).collect(Collectors.toList());
  }

  public List<Clothes> bySize(Size size, List<Clothes> list) {
    return list.stream().filter(c -> c.getSize() == size).collect(Collectors.toList());
  }

  public List<Clothes> byMaterial(Material material, List<Clothes> list) {
    return list.stream().filter(c -> c.getMaterial() == material).collect(Collectors.toList());
  }

  public List<Clothes> byCleanliness(Boolean clean, List<Clothes> list) {
    return list.stream().filter(c -> c.getClean() == clean).collect(Collectors.toList());
  }

  public List<Clothes> byColor(Color color, List<Clothes> list) {
    return list.stream().filter(c -> c.getColor().equals(color)).collect(Collectors.toList());
  }
}
