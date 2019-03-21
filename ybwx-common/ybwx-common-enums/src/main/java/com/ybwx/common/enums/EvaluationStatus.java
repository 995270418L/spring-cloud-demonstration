package com.ybwx.common.enums;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public enum EvaluationStatus implements BaseEnumInterface {

    UNTREATED(1, "未测评"),
    TREATED(2, "已测评");

    private static Map<Integer, EvaluationStatus> _MAP = Maps.newHashMap();

    static {
        for (EvaluationStatus orderStatus : EvaluationStatus.values()) {
            _MAP.put(orderStatus.getValue(), orderStatus);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    EvaluationStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static EvaluationStatus get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
