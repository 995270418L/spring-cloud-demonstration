package com.ybwx.common.enums.type;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Created by shixin on 4/14/16.
 */
public enum AdaptorType {
  ANBANG(1, "安邦"),
  HUIZE(2, "慧择-齐欣云"),
  DTB(3, "大特保"),
  NUOBEI(4, "诺贝"),
  YANGGUANG(5, "阳光"),
  APPOINTMENT(6, "预约"),
  TIANAN(7, "天安"),
  TAIKANG(8, "泰康"),
  WUKONG(9, "悟空保"),
  DATONG(10, "大同"),
  HUIZE_OLD(11, "慧择-OpenApi"),
  XIAOYUSAN(12, "小雨伞");

  private static Map<Integer, AdaptorType> _MAP = Maps.newHashMap();

  static {
    for (AdaptorType adaptorType : AdaptorType.values()) {
      _MAP.put(adaptorType.getValue(), adaptorType);
    }
    _MAP = Collections.unmodifiableMap(_MAP);
  }

  private int value;
  private String name;

  AdaptorType(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public static AdaptorType get(Integer value) {
    return _MAP.get(value);
  }

  public String getName() {
    return name;
  }

  public int getValue() {
    return value;
  }
}
