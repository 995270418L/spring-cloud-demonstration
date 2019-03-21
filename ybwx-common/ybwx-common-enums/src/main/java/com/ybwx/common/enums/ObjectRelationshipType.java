package com.ybwx.common.enums;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum ObjectRelationshipType implements BaseEnumInterface {

    ONESELF(1, "本人"),
    FATHER(2, "父亲"),
    MOTHER(3, "母亲"),
    SPOUSE(4, "配偶"),
    FAMILY_PILLAR(5, "家庭支柱", false),
    PARENT(6, "父母", false),
    CHILDREN(7, "孩子"),
    FAMILY(8, "家庭", false),
    SPOUSE_FATHER(9, "配偶的父亲"),
    SPOUSE_MOTHER(10, "配偶的母亲"),
    TARGET(100, "目标角色", false);//问卷问题使用

    private static Map<Integer, ObjectRelationshipType> _MAP = Maps.newHashMap();
    private static Set<ObjectRelationshipType> _REAL = Sets.newHashSet();
    private static Set<Integer> _REAL_VALUE = Sets.newHashSet();
    private static Set<ObjectRelationshipType> _ADULT = Sets.newHashSet(ONESELF, SPOUSE);
    private static Set<ObjectRelationshipType> _OLD_PEOPLE = Sets.newHashSet(FATHER, MOTHER, SPOUSE_FATHER, SPOUSE_MOTHER);
    private static Set<ObjectRelationshipType> _CHILDREN = Sets.newHashSet(CHILDREN);

    static {
        for (ObjectRelationshipType relationshipType : ObjectRelationshipType.values()) {
            _MAP.put(relationshipType.getValue(), relationshipType);
            if (relationshipType.real) {
                _REAL.add(relationshipType);
            }
        }
        _MAP = Collections.unmodifiableMap(_MAP);
        _REAL = Collections.unmodifiableSet(_REAL);
        _REAL_VALUE.addAll(_REAL.stream().map(e -> e.getValue()).collect(Collectors.toSet()));
        _REAL_VALUE = Collections.unmodifiableSet(_REAL_VALUE);
    }

    private int value;
    private String name;
    private boolean real = true;

    ObjectRelationshipType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    ObjectRelationshipType(int value, String name, boolean real) {
        this.value = value;
        this.name = name;
        this.real = real;
    }

    public static ObjectRelationshipType get(Integer value) {
        return _MAP.get(value);
    }

    public static Set<ObjectRelationshipType> getReal() {
        return _REAL;
    }

    public static Set<Integer> getRealValue() {
        return _REAL_VALUE;
    }

    public static Set<Long> getRealLongValue() {
        return _REAL_VALUE.stream().mapToLong(e -> e.longValue()).boxed().collect(Collectors.toSet());
    }

    public boolean isReal() {
        return _REAL_VALUE.contains(value);
    }
    public boolean isAdult() {
        return _ADULT.contains(this);
    }
    public boolean isOldPeople() {
        return _OLD_PEOPLE.contains(this);
    }
    public boolean isChildren() {
        return _CHILDREN.contains(this);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
