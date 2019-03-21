package com.ybwx.common.enums.type;

import com.google.common.collect.Maps;
import com.ybwx.common.enums.BaseEnumInterface;

import java.util.Collections;
import java.util.Map;

/**
 * Created by shixin on 4/6/16.
 * 被保人是投保人的xxx
 */
public enum RelationType implements BaseEnumInterface {
    UNKNOWN(0, "关系未知"),
    MYSELF(1, "本人"),
    PARENT(2, "父母"),
    CHILDREN(3, "子女"),
    LOVER(4, "配偶"),
    OTHER(6, "其他"),
    WIFE(11, "妻子"),
    HUSBAND(12, "丈夫"),
    FATHER_SON(33, "父子"),
    FATHER_DAUGHTER(34, "父女"),
    MOTHER_SON(35, "母子"),
    MOTHER_DAUGHTER(36, "母女"),
    FATHER(21, "父亲"),
    MOTHER(22, "母亲"),
    SON(31, "儿子"),
    DAUGHTER(32, "女儿");

    private static Map<Integer, RelationType> _MAP = Maps.newHashMap();

    static {
        for (RelationType relationType : RelationType.values()) {
            _MAP.put(relationType.getValue(), relationType);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    RelationType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static RelationType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
