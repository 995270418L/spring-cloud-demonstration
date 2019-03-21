package com.ybwx.common.mysql.util;

import com.ybwx.common.mysql.bean.Column;
import com.ybwx.common.mysql.bean.Table;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.*;

/**
 * @author pst
 */
public class DBUtil {

    public static Properties config;
    public static Connection conn;

    static {
        try {
            config = new Properties();
            String path = "ybwx-common\\ybwx-common-mysql\\src\\main\\resources\\config.properties";
            FileInputStream is = null;
            try {
                is = new FileInputStream(path);
                config.load(is);
            } catch (FileNotFoundException e) {
                FileOutputStream fos = new FileOutputStream(path);
                setDefaultValue();
                config.store(fos, null);
                if (fos != null) {
                    fos.close();
                }
                System.exit(1);
            } finally {
                if (is != null) {
                    is.close();
                }
            }
            initProperties();
            Class.forName(config.getProperty("mysql.className"));
            conn = DriverManager.getConnection(
                    config.getProperty("mysql.url"),
                    config.getProperty("mysql.user"),
                    config.getProperty("mysql.password"));
            if (conn.isClosed()) {
                System.out.println("closed connection");
            } else {
                System.out.println("open connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void setDefaultValue() {
        config.setProperty("mysql.className", "com.mysql.jdbc.Driver");
        config.setProperty("mysql.url", "jdbc:mysql://mysql0.res.nuobei.cn:3306/test");
        config.setProperty("mysql.user", "root");
        config.setProperty("mysql.password", "123456");
        //jdbcType-JavaType
        config.setProperty("bigint", "Long");
        config.setProperty("mediumint", "Integer");
        config.setProperty("integer", "Integer");
        config.setProperty("smallint", "Integer");
        config.setProperty("tinyint", "Integer");
        config.setProperty("int", "Integer");
        config.setProperty("char", "String");
        config.setProperty("varchar", "String");
        config.setProperty("text", "String");
        config.setProperty("mediumtext", "String");
        config.setProperty("float", "Float");
        config.setProperty("double", "Double");
        config.setProperty("mediumtext", "String");
        config.setProperty("date", "java.util.Date");
        config.setProperty("timestamp", "java.util.Date");
        config.setProperty("datetime", "java.util.Date");
        config.setProperty("longtext", "String");
    }

    private static void initProperties() {
        String url = config.getProperty("mysql.url");
        int index = url.lastIndexOf("/") + 1;
        int last = url.lastIndexOf("?");
        last = (last == -1) ? url.length() : last;
        String schema = url.substring(index, last);
        config.setProperty("mysql.schema", schema);
    }

    public static Table getTable(String tableName) {
        Table table = null;
        String sql = "select COLUMN_NAME,DATA_TYPE,COLUMN_KEY,COLUMN_COMMENT " +
                "from information_schema.COLUMNS " +
                "where TABLE_SCHEMA=? " +
                "and TABLE_NAME=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, config.getProperty("mysql.schema"));
            ps.setString(2, tableName);
            ResultSet rs = ps.executeQuery();

            Set<String> dataTypeSet = new HashSet<String>();
            Column columnKey = null;
            Column columnKeyCollection = null;
            List<Column> columnList = new ArrayList<Column>();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String columnDataType = rs.getString("DATA_TYPE");
                String columnComment = rs.getString("COLUMN_COMMENT");
                String propertyName = getVariable(columnName);

                String typeHandler = null;
                String propertyDataType = config.getProperty(tableName + "." + columnName);
                if (propertyDataType == null) {
                    propertyDataType = config.getProperty(columnDataType);
                    if (propertyDataType == null) {
                        throw new RuntimeException("columnDataType:" + columnDataType + "在properties文件里找不到");
                    }
                } else {
                    String[] split = propertyDataType.split("\\|");
                    propertyDataType = split[0];
                    typeHandler = split[1];
                }

                propertyDataType = propertyDataType.trim();
                int index = propertyDataType.lastIndexOf(".");
                if (index != -1) {
                    dataTypeSet.add(propertyDataType);
                    propertyDataType = propertyDataType.substring(index + 1);
                }
                Column column = new Column(
                        columnName,
                        columnDataType,
                        columnComment,
                        propertyName,
                        propertyDataType,
                        typeHandler,
                        null);
                if (columnKey == null
                        && "PRI".equalsIgnoreCase(rs.getString("COLUMN_KEY"))
                        /*&& "auto_increment".equalsIgnoreCase(rs.getString("EXTRA"))*/) {
                    columnKey = column;
                    columnKeyCollection = (Column) column.clone();
                    columnKeyCollection.setPropertyNameCollection(columnKeyCollection.getPropertyName() + "Collection");
                } else {
                    columnList.add(column);
                }
            }
            /*if (columnKey == null) {
                throw new SQLException("columnKey is null");
            }*/
            if (columnList.isEmpty()) {
                throw new SQLException("columnList is empty");
            }
            if (dataTypeSet.isEmpty()) {
                dataTypeSet = null;
            }
            table = new Table(columnKey, columnKeyCollection, columnList, dataTypeSet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return table;
    }

    public static String getVariable(String columnName) {
        StringBuilder sb = new StringBuilder(columnName.toLowerCase());
        int fromIndex = 0;
        int index = -1;
        while ((index = sb.indexOf("_", fromIndex)) != -1) {
            sb.replace(index, index + 2, sb.substring(index + 1, index + 2).toUpperCase());
            fromIndex = index + 1;
        }
        return sb.toString();
    }

}
