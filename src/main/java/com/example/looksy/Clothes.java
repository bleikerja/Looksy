package com.example.looksy;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
public class Clothes {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private final String color;
  private final String size;
  private final String seasonUsage;
  private final String type;
  private final String material;
  private Boolean clean;
  private String washingNotes;

  public Clothes(String color, String size, String seasonUsage, String type, String material, Boolean clean) {
    this.color = color;
    this.size = size;
    this.seasonUsage = seasonUsage;
    this.type = type;
    this.material = material;
    this.clean = clean;
  }

  public String getType() {
    return type;
  }
  public Boolean getClean() {
    return clean;
  }
  public void setClean(Boolean clean) {
    this.clean = clean;
  }
  public String getWashingNotes() {
    return Objects.requireNonNullElse(washingNotes, "");//ToDo: return Exeption
  }
  public void setWashingNotes(String washingNotes) {
    this.washingNotes = washingNotes;
  }

  public String getColor() {
    return color;
  }
  public String getSize() {
    return size;
  }
  public String getSeasonUsage() {
    return seasonUsage;
  }
  public String getMaterial() {
    return material;
  }
}
