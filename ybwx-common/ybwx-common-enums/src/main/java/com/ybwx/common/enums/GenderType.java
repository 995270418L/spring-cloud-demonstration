package com.ybwx.common.enums;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Created by shixin on 2/29/16.
 */
public enum GenderType implements BaseEnumInterface {
    UNKNOWN(0, "无限制", "无限制"),
    MALE(1, "M", "男"),
    FEMALE(2, "F", "女");

    private static Map<Integer, GenderType> _MAP = Maps.newHashMap();

    static {
        for (GenderType gender : GenderType.values()) {
            _MAP.put(gender.getValue(), gender);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;
    private String chinese;

    GenderType(int value, String name, String chinese) {
        this.value = value;
        this.name = name;
        this.chinese = chinese;
    }

    public static GenderType get(String chineseValue) {
        if ("男".equalsIgnoreCase(chineseValue)) {
            return GenderType.MALE;
        }
        if ("女".equalsIgnoreCase(chineseValue)) {
            return GenderType.FEMALE;
        }
        throw new RuntimeException("invalid gender:" + chineseValue);
    }

    public static GenderType getOpositeGender(GenderType gender) {
        if (gender == MALE) {
            return FEMALE;
        }
        if (gender == FEMALE) {
            return MALE;
        }
        throw new IllegalStateException("Invalid gender enum");
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getChinese() {
        return chinese;
    }

    public static GenderType get(Integer value) {
        return _MAP.get(value);
    }
}
