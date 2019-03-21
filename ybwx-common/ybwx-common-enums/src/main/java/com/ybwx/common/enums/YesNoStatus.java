package com.ybwx.common.enums;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Created by shixin on 1/19/16.
 */
public enum YesNoStatus implements BaseEnumInterface {

    NO(0, "否"),
    YES(1, "是");

    private static Map<Integer, YesNoStatus> _MAP = Maps.newHashMap();

    static {
        for (YesNoStatus insuranceOrderStatus : YesNoStatus.values()) {
            _MAP.put(insuranceOrderStatus.getValue(), insuranceOrderStatus);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    YesNoStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static YesNoStatus get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Boolean getBool() {
        return value == 1;
    }

    public static YesNoStatus get(Boolean value) {
        if (value == null) {
            return NO;
        }
        if (value) {
            return YES;
        } else {
            return NO;
        }
    }
}
