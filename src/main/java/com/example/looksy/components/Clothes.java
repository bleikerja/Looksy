package com.example.looksy.components;

import java.awt.Color;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

@Entity
public class Clothes {
  @JsonSerialize(using = ColorSerializer.class)
  private Color color;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Size size;
  private Season seasonUsage;
  private Type type;
  private Material material;
  private Boolean clean;
  private WashingNotes washingNotes;

  @JsonCreator
  public Clothes(@JsonProperty("color") Color color,
                 @JsonProperty("size") Size size,
                 @JsonProperty("seasonUsage") Season seasonUsage,
                 @JsonProperty("type") Type type,
                 @JsonProperty("material") Material material,
                 @JsonProperty("clean") Boolean clean) {
    if (color == null || size == null || seasonUsage == null || type == null || material == null || clean == null) {
      throw new IllegalArgumentException("color or size or season or type or material or clean are null");
    }
    this.color = color;
    this.size = size;
    this.seasonUsage = seasonUsage;
    this.type = type;
    this.material = material;
    this.clean = clean;
  }

  public Clothes(@JsonProperty("color") Color color,
                 @JsonProperty("size") Size size,
                 @JsonProperty("seasonUsage") Season seasonUsage,
                 @JsonProperty("type") Type type,
                 @JsonProperty("material") Material material,
                 @JsonProperty("clean") Boolean clean,
                 @JsonProperty("washingNotes") WashingNotes washingNotes) {
    this(color, size, seasonUsage, type, material, clean);
    this.washingNotes = washingNotes;
  }

  protected Clothes() {}

  public Type getType() {
    return type;
  }
  public Boolean getClean() {
    return clean;
  }
  public void setClean(Boolean clean) {
    this.clean = clean;
  }
  public WashingNotes getWashingNotes() {
    return washingNotes;
  }
  public void setWashingNotes(WashingNotes washingNotes) {
    this.washingNotes = washingNotes;
  }

  public Color getColor() {
    return color;
  }
  public Size getSize() {
    return size;
  }
  public Season getSeasonUsage() {
    return seasonUsage;
  }
  public Material getMaterial() {
    return material;
  }
}
