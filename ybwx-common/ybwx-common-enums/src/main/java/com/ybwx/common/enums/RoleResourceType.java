package com.ybwx.common.enums;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public enum RoleResourceType implements BaseEnumInterface {

    MENU(1, "菜单"),
    FUNCTION(2, "功能");

    private static Map<Integer, RoleResourceType> _MAP = Maps.newHashMap();

    static {
        for (RoleResourceType orderStatus : RoleResourceType.values()) {
            _MAP.put(orderStatus.getValue(), orderStatus);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    RoleResourceType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static RoleResourceType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
