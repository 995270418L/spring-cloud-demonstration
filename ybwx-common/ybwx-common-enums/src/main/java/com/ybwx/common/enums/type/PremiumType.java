package com.ybwx.common.enums.type;

import com.google.common.collect.Maps;
import com.ybwx.common.enums.BaseEnumInterface;

import java.util.Collections;
import java.util.Map;

/**
 * Created by shixin on 4/21/16.
 */
public enum PremiumType implements BaseEnumInterface {
    UNKNOWN(0, "保费类型未知"),
    FIXED(1, "固定保费"),
    ADAPTOR_TRIAL(2, "第三方试算接口"),
    SERVICE_TRIAL(3, "新版试算服务");

    private static Map<Integer, PremiumType> _MAP = Maps.newHashMap();

    static {
        for (PremiumType priceType : PremiumType.values()) {
            _MAP.put(priceType.getValue(), priceType);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    PremiumType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static PremiumType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
