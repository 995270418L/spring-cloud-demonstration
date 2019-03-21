package com.ybwx.common.enums.type;

import com.google.common.collect.Maps;
import com.ybwx.common.enums.BaseEnumInterface;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * Created by shixin on 1/27/16.
 */
public enum ChargeFrequencyType implements BaseEnumInterface, Serializable {
    BY_YEAR(1, "按年缴"),
    BY_MONTH(2, "按月缴");

    private static Map<Integer, ChargeFrequencyType> _MAP = Maps.newHashMap();

    static {
        for (ChargeFrequencyType chargeFrequencyType : ChargeFrequencyType.values()) {
            _MAP.put(chargeFrequencyType.getValue(), chargeFrequencyType);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    ChargeFrequencyType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ChargeFrequencyType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
