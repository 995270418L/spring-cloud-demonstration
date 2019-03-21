package com.ybwx.common.util.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by shixin on 1/7/16.
 */
public class JSONSnakeUtils {
  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.getDeserializationConfig().with(PropertyNamingStrategy.SNAKE_CASE);
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  public static <T> List<T> readListValue(String jsonContent, Class<T> clazz) {
    return JSONUtils.readListValue(jsonContent, clazz, objectMapper);
  }

  public static <K, V> Map<K, V> readMapValue(String jsonContent, Class<K> kClazz, Class<V> vClazz) {
    return JSONUtils.readMapValue(jsonContent, kClazz, vClazz, objectMapper);
  }

    public static <K, V> Map<K, List<V>> readMapListValue(String jsonContent, Class<K> kClazz, Class<V> vClazz) {
        return JSONUtils.readMapListValue(jsonContent, kClazz, vClazz, objectMapper);
    }

  public static <T> T readValue(String jsonContent, Class<T> clazz) {
    return JSONUtils.readValue(jsonContent, clazz, objectMapper);
  }

  public static <T> List<T> readJsonFileList(File file, Class<T> clazz) {
    return JSONUtils.readJsonFileList(file, clazz, objectMapper);
  }

    public static JsonNode readJsonNode(String jsonContent) {
        return JSONUtils.readJsonNode(jsonContent, objectMapper);
    }

  public static <T> T readValueIgnoreException(String jsonContent, Class<T> clazz) {
    return JSONUtils.readValueIgnoreException(jsonContent, clazz, objectMapper);
  }

  public static String writeValue(Object obj) {
    return JSONUtils.writeValue(obj, objectMapper);
  }

  public static String writePrettyValue(Object obj) {
    return JSONUtils.writePrettyValue(obj, objectMapper);
  }

  /**
   * 验证字符串符合 JSON 规范
   *
   * @param json
   * @return
   */
  public static boolean validateJson(String json) {
    return JSONUtils.validateJson(json);
  }
}
