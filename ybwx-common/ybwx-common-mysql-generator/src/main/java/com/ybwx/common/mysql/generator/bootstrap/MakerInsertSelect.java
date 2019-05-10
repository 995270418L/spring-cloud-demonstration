package com.ybwx.common.mysql.generator.bootstrap;

import com.ybwx.common.mysql.generator.bean.Table;
import com.ybwx.common.mysql.generator.util.CodeUtil;
import com.ybwx.common.mysql.generator.util.DBUtil;
import com.ybwx.common.mysql.generator.util.FileUtil;
import com.ybwx.common.mysql.generator.util.VelocityUtil;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MakerInsertSelect {
    private static final String ENC = "UTF-8";

    public MakerInsertSelect() {
        try {
            VelocityUtil.initVelocityEngine("ybwx-common/ybwx-common-mysql/src/main/java/com/ybwx/common/mysql/resource");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void make(String basePackage, String modular, Map<String, List<String>> groupMap, String tablePrefix) {
//        String outputRootApi = outputRoot + modular + "\\" + modular + "-api\\src\\main\\java";
        String outputRootApi = modular + "\\src\\main\\java";
//        String outputRootProvider = outputRoot + modular + "\\" + modular + "-provider\\src\\main\\java";
        for (Entry<String, List<String>> entry : groupMap.entrySet()) {
            String group = entry.getKey();
            List<String> groupList = entry.getValue();
            for (String tableName : groupList) {
                Table table = DBUtil.getTable(tableName);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("util", new CodeUtil());
                data.put("table", table);
                data.put("tableName", tableName);

                makeJava(data, basePackage, group, tableName, tablePrefix, "entity", "Entity", outputRootApi, "entity.vm");

//				param
                makeJava(data, basePackage, group, tableName, tablePrefix, "param", "QueryParam", outputRootApi, "param.vm");

//				service
                makeJava(data, basePackage, group, tableName, tablePrefix, "service", "Service", outputRootApi, "service.vm");

//				dao
                makeJava(data, basePackage, group, tableName, tablePrefix, "mapper", "EntityMapper", outputRootApi, "mapper.vm");

//				ServiceImpl
                makeJava(data, basePackage, group, tableName, tablePrefix, "service.impl", "ServiceImpl", outputRootApi, "service.impl.vm");

//				xml
                makeFile(data, "xml-insert-select.vm", outputRootApi + "\\..\\resources\\mybatis", CodeUtil.convertClassNameToPath((String) data.get("mapperName"), "xml"));
            }
        }
    }


    protected void makeJava(Map<String, Object> data, String basePackage, String group, String tableName, String tablePrefix, String common, String variableSuffix, String outputRoot, String template) {
        String classPackage = CodeUtil.getPackage(basePackage, common, group);
        String variable = CodeUtil.getVariable(tableName.substring(tablePrefix.length())) + variableSuffix;
        String name = CodeUtil.getUpperCaseVariable(variable);
        String className = CodeUtil.getClassName(classPackage, name);
        common = CodeUtil.standard(common);
        data.put(common + "Package", classPackage);
        data.put(common + "Variable", variable);
        data.put(common + "Name", name);
        data.put(common + "ClassName", className);
        makeFile(data, template, outputRoot, CodeUtil.convertClassNameToPath(className, "java"));
    }

    protected void makeFile(Map<String, Object> data, String resource, String outputRoot, String path) {
        BufferedWriter bw = null;
        try {
            File file = FileUtil.getFile(outputRoot, path);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), ENC));
            VelocityUtil.merge(data, resource, bw, ENC);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
