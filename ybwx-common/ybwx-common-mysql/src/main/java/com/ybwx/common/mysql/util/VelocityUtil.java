package com.ybwx.common.mysql.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.Writer;
import java.util.Map;

/**
 * @author pst
 */
public class VelocityUtil {

    private static final String FILE_RESOURCE_LOADER_PATH = "file.resource.loader.path";
    private static VelocityEngine velocity;

    public static void initVelocityEngine(String path) throws Exception {
        velocity = new VelocityEngine();
        velocity.setProperty(FILE_RESOURCE_LOADER_PATH, path);
        velocity.init();
    }

    public static void merge(Map<String, Object> data, String resource, Writer writer, String encoding) throws Exception {
        Template temp = velocity.getTemplate(resource, encoding);
        temp.merge(new VelocityContext(data), writer);
    }

}
