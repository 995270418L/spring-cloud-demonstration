package com.ybwx.common.util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {

  public static <K, V> MapBuilder<K, V> builder() {
    return new MapBuilder<>();
  }

  private Map<K, V> map = new HashMap<>();

  public MapBuilder<K, V> put(final K k1, final V v1) {
    this.map.put(k1, v1);
    return this;
  }

  public Map<K, V> build() {
    return this.map;
  }
}
