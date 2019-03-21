package com.ybwx.common.enums.type;

import com.google.common.collect.Maps;
import com.ybwx.common.enums.BaseEnumInterface;

import java.util.Collections;
import java.util.Map;

/**
 * 产品需要的证件类型
 * Created by shixin on 3/10/16.
 */
public enum IdType implements BaseEnumInterface {
    UNKNOWN(0, "证件类型未知", 11),
    ID_CARDS(1, "身份证", 1),
    PASSPORT(2, "护照", 2),
    BIRTH_CERTIFICATE(3, "出生证", 3),
    DRIVING_LICENSE(4, "驾照", 7),
    HONG_KONG_AND_MACAO_PASSPORT(5, "港澳通行证", 6),
    MILITARY_ID(6, "军官证", 4),
    TAIWAN_COMPATRIOTS_PASSPORT(7, "台胞证", 8),
    POLICE_DOCUMENTS(8, "警察证", 9),
    RESIDENCE_BOOKLET(9, "户口簿", 5),
    OTHERS(99, "其他", 12),
    NO_ID(100, "无证件", 10);

    private static Map<Integer, IdType> _MAP = Maps.newHashMap();

    static {
        for (IdType insuranceStatus : IdType.values()) {
            _MAP.put(insuranceStatus.getValue(), insuranceStatus);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;
    private int sequence;

    IdType(int value, String name, int sequence) {
        this.value = value;
        this.name = name;
        this.sequence = sequence;
    }

    public static IdType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getSequence() {
        return sequence;
    }
}
