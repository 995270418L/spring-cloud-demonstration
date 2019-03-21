package com.ybwx.common.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @描述 :  启用或者禁用
 * @作者 :	pst
 * @日期 :	2017/10/10
 * @时间 :	12:01
 */
public enum EnableDisableStatus implements BaseEnumInterface {

    DISABLE(0, "禁用"),
    ENABLE(1, "启用");

    private static Map<Integer, EnableDisableStatus> _MAP = new HashMap<>();

    static {
        for (EnableDisableStatus elementType : EnableDisableStatus.values()) {
            _MAP.put(elementType.getValue(), elementType);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    EnableDisableStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static EnableDisableStatus get(int value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
