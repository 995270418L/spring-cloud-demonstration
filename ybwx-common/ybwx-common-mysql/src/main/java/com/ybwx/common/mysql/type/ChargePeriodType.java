package com.ybwx.common.mysql.type;

import com.google.common.collect.Maps;
import com.ybwx.common.enums.BaseEnumInterface;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * Created by shixin on 1/27/16.
 */
public enum ChargePeriodType implements BaseEnumInterface, Serializable {
    UNKNOWN(0, "缴费期间类型未知"),
    BY_ONCE(1, "趸交"),
    BY_YEAR(2, "按年缴"),
    BY_MONTH(3, "按月缴"),
    BY_AGE(4, "按年龄缴");

    private static Map<Integer, ChargePeriodType> _MAP = Maps.newHashMap();

    static {
        for (ChargePeriodType chargePeriodType : ChargePeriodType.values()) {
            _MAP.put(chargePeriodType.getValue(), chargePeriodType);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    ChargePeriodType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ChargePeriodType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
