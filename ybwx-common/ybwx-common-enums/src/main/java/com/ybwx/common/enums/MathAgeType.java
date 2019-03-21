package com.ybwx.common.enums;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * 年龄计算类型
 */
public enum MathAgeType implements BaseEnumInterface {
    BIRTHDAY_SAME_DAY(1, "生日当天年龄加一"),
    BIRTHDAY_SECOND_DAY(2, "生日第二天年龄加一");

    private static Map<Integer, MathAgeType> _MAP = Maps.newHashMap();

    static {
        for (MathAgeType insuranceOrderStatus : MathAgeType.values()) {
            _MAP.put(insuranceOrderStatus.getValue(), insuranceOrderStatus);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    MathAgeType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static MathAgeType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
