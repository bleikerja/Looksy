package com.example.looksy.components;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.Color;
import java.io.IOException;

public class ColorDeserializer extends JsonDeserializer<Color> {
  @Override
  public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    int red = node.get("red").intValue();
    int green = node.get("green").intValue();
    int blue = node.get("blue").intValue();
    return new Color(red, green, blue);
  }
}
