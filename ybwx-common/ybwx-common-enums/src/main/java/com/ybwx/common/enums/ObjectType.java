package com.ybwx.common.enums;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public enum ObjectType implements BaseEnumInterface {

    PERSON(1, "人"),
    HOUSE(2, "房"),
    CAR(3, "车"),
    PET(4, "宠物"),
    FAMILY(5, "家庭");

    private static Map<Integer, ObjectType> _MAP = Maps.newHashMap();

    static {
        for (ObjectType orderStatus : ObjectType.values()) {
            _MAP.put(orderStatus.getValue(), orderStatus);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    ObjectType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ObjectType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
