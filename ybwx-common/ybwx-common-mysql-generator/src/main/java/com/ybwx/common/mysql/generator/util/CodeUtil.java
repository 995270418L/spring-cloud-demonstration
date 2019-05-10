package com.ybwx.common.mysql.generator.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author pst
 */
public class CodeUtil {

    public static boolean isInvalid(Object t) {
        if (t == null) {
            return true;
        }
        if (t instanceof String && ((String) t).trim().isEmpty()) {
            return true;
        }
        if (t instanceof Collection<?> && ((Collection<?>) t).isEmpty()) {
            return true;
        }
        return t instanceof Map<?, ?> && ((Map<?, ?>) t).isEmpty();
    }

    public static <T> Collection<Collection<T>> splitCollection(Collection<T> collection, int limit) {
        Collection<Collection<T>> splitCollection = new ArrayList<Collection<T>>();
        int size = collection.size();
        if (size <= limit) {
            splitCollection.add(collection);
        } else {
            List<T> list = new ArrayList<T>(collection);
            int fromIndex = 0;
            int toIndex = limit;
            List<T> subList = null;
            do {
                subList = list.subList(fromIndex, toIndex);
                splitCollection.add(subList);
                fromIndex += limit;
                toIndex += limit;
                if (toIndex > size) {
                    toIndex = size;
                }
            } while (fromIndex < toIndex);
        }
        return splitCollection;
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

    public static String convertClassNameToPath(String className, String ext) {
        return className.replaceAll("\\.", "/") + "." + ext;
    }

    public static String getClassName(String classPackage, String name) {
        return classPackage + "." + name;
    }

    public static String getPackage(String basePackage, String... subPackageArray) {
        StringBuilder sb = new StringBuilder(basePackage);
        for (String subPackage : subPackageArray) {
            if (subPackage != null) {
                sb.append(".").append(subPackage);
            }
        }
        return sb.toString().toLowerCase();
    }

    public static String getUpperCaseVariable(String variable) {
        StringBuilder sb = new StringBuilder(variable);
        sb.replace(0, 1, sb.substring(0, 1).toUpperCase());
        return sb.toString();
    }

    public static String standard(String value) {
        String[] split = value.split("\\.");
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String s : split) {
            if (first) {
                first = false;
                sb.append(s);
            } else {
                sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
            }
        }
        return sb.toString();
    }
}
