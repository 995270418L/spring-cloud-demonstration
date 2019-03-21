package com.ybwx.common.mysql.type;

import com.google.common.collect.Maps;
import com.ybwx.common.enums.BaseEnumInterface;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * Created by shixin on 1/27/16.
 */
public enum CoveragePeriodType implements BaseEnumInterface, Serializable {
    UNKNOWN(0, "保障期间类型未知"),
    WHOLE_LIFE(1, "保终身"),
    BY_YEAR(2, "按年保"),
    BY_AGE(3, "按年龄限保"),
    BY_MONTH(4, "按月保"),
    BY_DAY(5, "按天保");

    private static Map<Integer, CoveragePeriodType> _MAP = Maps.newHashMap();

    static {
        for (CoveragePeriodType coveragePeriodType : CoveragePeriodType.values()) {
            _MAP.put(coveragePeriodType.getValue(), coveragePeriodType);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    CoveragePeriodType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static CoveragePeriodType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
