package com.ybwx.common.mysql;

import com.ybwx.common.mysql.bootstrap.MakerInsertSelect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockDbInsertSelectConfigMaker {

    public static void main(String[] args) {
        Map<String, List<String>> groupMap = new HashMap<String, List<String>>();
        List<String> groupList;
        String tablePrefix;

        groupList = new ArrayList<>();
        groupList.add("demo_stock");
        groupMap.put(null, groupList);

        tablePrefix = "demo_";

        new MakerInsertSelect().make(
                "com.ybwx.stock",
                "ybwx-stock",
                groupMap, tablePrefix);

    }
}
