package com.ybwx.common.enums.type;

import com.ybwx.common.enums.BaseEnumInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shixin on 6/15/16.
 */
public enum EffectiveDayType implements BaseEnumInterface {
    UNKNOWN(0, "未知"),
    INTERVAL_VALUE(1, "区间值"),
    DESIGNATED(2, "指定值");

    private static Map<Integer, EffectiveDayType> _MAP = new HashMap<>();

    static {
        for (EffectiveDayType elementType : EffectiveDayType.values()) {
            _MAP.put(elementType.getValue(), elementType);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    EffectiveDayType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static EffectiveDayType get(int value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
