package com.ybwx.common.util;

import com.google.common.collect.Lists;

import java.util.List;

public class ListBuilder<K> {

  public static <K> ListBuilder<K> builder() {
    return new ListBuilder<>();
  }

  private List<K> list = Lists.newArrayList();

  public ListBuilder<K> add(final K k1) {
    this.list.add(k1);
    return this;
  }

  public List<K> build() {
    return this.list;
  }
}
