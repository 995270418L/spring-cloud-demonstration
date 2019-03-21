package com.ybwx.common.enums;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * 年龄计算类型
 */
public enum MathCoverageType implements BaseEnumInterface {
    EFFECTIVE_DATE(1, "生效时间"),
    CURRENT_DATE(2, "当前时间");

    private static Map<Integer, MathCoverageType> _MAP = Maps.newHashMap();

    static {
        for (MathCoverageType insuranceOrderStatus : MathCoverageType.values()) {
            _MAP.put(insuranceOrderStatus.getValue(), insuranceOrderStatus);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    MathCoverageType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static MathCoverageType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
