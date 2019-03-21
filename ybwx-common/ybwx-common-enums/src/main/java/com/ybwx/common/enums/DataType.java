package com.ybwx.common.enums;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public enum DataType implements BaseEnumInterface {

    INTEGER_RANGE(1, "整数范围"),
    DATE_TIME_RANGE(2, "日期范围"),
    RADIO_ENUMS(3, "单选枚举"),
    YES_NO(4, "是否"),
    STRENGTH(5, "强度"),
    MULTISELECT_ENUMS(6, "多选枚举"),
    FEEDBACK(7, "用户反馈");

    private static Map<Integer, DataType> _MAP = Maps.newHashMap();

    static {
        for (DataType orderStatus : DataType.values()) {
            _MAP.put(orderStatus.getValue(), orderStatus);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    DataType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static DataType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
