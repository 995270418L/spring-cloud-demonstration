package com.ybwx.common.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybwx.common.enums.DataType;

public class DataTypeUtils {

    private final static String DEFAULT_KEY = "default";
    private final static String MIN_KEY = "min";
    private final static String ENUM_KEY = "enum";

    public static boolean checkValue(Object value, DataType dataType, String config) {
        // TODO 未实现
        return true;
    }

    public static Object getDefaultValue(DataType dataType, String config) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(config);
        switch (dataType) {
            case INTEGER_RANGE:
                if (jsonObject.containsKey(DEFAULT_KEY)) {
                    return jsonObject.getInteger(DEFAULT_KEY);
                }
                return jsonObject.getInteger(MIN_KEY);
            case DATE_TIME_RANGE:
                // TODO 不知道怎么写
                return null;
            case RADIO_ENUMS:
                if (jsonObject.containsKey(DEFAULT_KEY)) {
                    return jsonObject.getInteger(DEFAULT_KEY);
                }
                return jsonObject.getJSONArray(ENUM_KEY).getInteger(0);
            case YES_NO:
                return jsonObject.getInteger(DEFAULT_KEY);
            case STRENGTH:
                if (jsonObject.containsKey(DEFAULT_KEY)) {
                    return jsonObject.getInteger(DEFAULT_KEY);
                }
                return jsonObject.getInteger(MIN_KEY);
            case MULTISELECT_ENUMS:
            case FEEDBACK:
                if (jsonObject.containsKey(DEFAULT_KEY)) {
                    return jsonObject.getJSONArray(DEFAULT_KEY);
                }
                return new JSONArray();
            default:
                throw new Exception("未找到的数据类型");
        }
    }

}
