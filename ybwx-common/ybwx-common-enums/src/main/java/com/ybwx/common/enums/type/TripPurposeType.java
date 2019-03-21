package com.ybwx.common.enums.type;

import com.google.common.collect.Maps;
import com.ybwx.common.enums.BaseEnumInterface;

import java.util.Collections;
import java.util.Map;

public enum TripPurposeType implements BaseEnumInterface {
    TRAVEL(1, "旅游"),
    BUSINESS(2, "商务"),
    FAMILY_VISIT(3, "探亲"),
    STUDYING_ABROAD(4, "留学"),
    WORKERS(5, "务工"),
    OTHER(6, "其他");

    private static Map<Integer, TripPurposeType> _MAP = Maps.newHashMap();

    static {
        for (TripPurposeType item : TripPurposeType.values()) {
            _MAP.put(item.getValue(), item);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    TripPurposeType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TripPurposeType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
