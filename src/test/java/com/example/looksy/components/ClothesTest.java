package com.example.looksy.components;

import org.junit.jupiter.api.Test;

import java.awt.*;

public class ClothesTest {
  Clothes clothes;
  @Test
  public void  testClass() {
    clothes = new Clothes(Color.BLUE, Size._M, Season.Summer, Type.Pants, Material.Cotton, true);

  }
}
