package com.ybwx.common.mysql.generator;

import com.ybwx.common.mysql.generator.bootstrap.MakerInsertSelect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDbInsertSelectConfigMaker {

    public static void main(String[] args) {
        Map<String, List<String>> groupMap = new HashMap<String, List<String>>();
        List<String> groupList;
        String tablePrefix;

        groupList = new ArrayList<>();
//        groupList.add("spark_evaluation_scheme");
//        groupList.add("spark_vector");
        groupList.add("demo_order");
        groupMap.put(null, groupList);

        tablePrefix = "demo_";

        new MakerInsertSelect().make(
                "com.ybwx.order",
                "ybwx-order",
                groupMap, tablePrefix);

    }
}
