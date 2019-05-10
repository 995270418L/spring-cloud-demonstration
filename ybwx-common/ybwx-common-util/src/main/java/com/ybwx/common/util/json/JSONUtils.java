package com.ybwx.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;
import java.util.Map;

/**
 * Created by shixin on 1/7/16.
 */
@Slf4j
public class JSONUtils {

  protected static <T> List<T> readJsonFileList(File src, Class<T> clazz, ObjectMapper objectMapper) {
    try {
      JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
      return objectMapper.readValue(src, javaType);
    } catch (IOException e) {
      log.error("Fail to convert src file [{}] to bean [{}]", src.getName(), clazz, e);
      throw new IllegalStateException("Fail to parse json str");
    }
  }

  protected static <T> List<T> readListValue(String jsonContent, Class<T> clazz, ObjectMapper objectMapper) {
    try {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
      return objectMapper.readValue(jsonContent, javaType);
    } catch (IOException e) {
      log.error("Fail to convert jsonArray[{}] to List<bean> [{}]", jsonContent, clazz, e);
      throw new IllegalStateException("Fail to parse json str");
    }
  }

  protected static <K, V> Map<K, V> readMapValue(String jsonContent, Class<K> kClazz, Class<V> vClazz, ObjectMapper objectMapper) {
    try {
      JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, kClazz, vClazz);
      return objectMapper.readValue(jsonContent, javaType);
    } catch (IOException e) {
      log.error("Fail to convert jsonMap[{}] to Map<key,value> [{},{}]", jsonContent, kClazz, vClazz, e);
      throw new IllegalStateException("Fail to parse json str");
    }
  }

    protected static <K, V> Map<K, List<V>> readMapListValue(String jsonContent, Class<K> kClazz, Class<V> vClazz, ObjectMapper objectMapper) {
        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            return objectMapper.readValue(jsonContent, typeFactory.constructParametricType(Map.class, typeFactory.constructType(kClazz), typeFactory.constructParametricType(List.class, vClazz)));
        } catch (IOException e) {
            log.error("Fail to convert jsonMap[{}] to Map<key,List<value>> [{},{}]", jsonContent, kClazz, vClazz, e);
            throw new IllegalStateException("Fail to parse json str");
        }
    }

  protected static <T> T readValue(String jsonContent, Class<T> clazz, ObjectMapper objectMapper) {
    try {
      return objectMapper.readValue(jsonContent, clazz);
    } catch (IOException e) {
      log.error("Fail to convert json[{}] to bean[{}]", jsonContent, clazz, e);
      throw new IllegalStateException("Fail to parse json str");
    }
  }

  protected static <T> T readValue(InputStream jsonContent, Class<T> clazz, ObjectMapper objectMapper) {
    try {
      return objectMapper.readValue(jsonContent, clazz);
    } catch (IOException e) {
      log.error("Fail to convert json[{}] to bean[{}]", jsonContent, clazz, e);
      throw new IllegalStateException("Fail to parse json str");
    }
  }

  protected static <T> T readValueIgnoreException(String jsonContent, Class<T> clazz, ObjectMapper objectMapper) {
    try {
      return objectMapper.readValue(jsonContent, clazz);
    } catch (IOException e) {
      log.error("Fail to convert json[{}] to bean[{}]", jsonContent, clazz, e);
      return null;
    }
  }

  protected static String writeValue(Object obj, ObjectMapper objectMapper) {
    if (obj == null) {
      return null;
    }

    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error("failed to process json obj", e);
      return StringUtils.EMPTY;
    }
  }

  protected static String writePrettyValue(Object obj, ObjectMapper objectMapper) {
    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error("failed to process json obj", e);
      return StringUtils.EMPTY;
    }
  }

  /**
   * 验证字符串符合 JSON 规范
   *
   * @param json
   * @return
   */
  protected static boolean validateJson(String json) {
    return new JsonValidator().validate(json);
  }

    public static JsonNode readJsonNode(String jsonContent, ObjectMapper objectMapper) {
        try {
            return objectMapper.readTree(jsonContent);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

  private static class JsonValidator {
    private CharacterIterator it;
    private char c;
    private int col;

    public JsonValidator() {
    }

    /**
     * 验证一个字符串是否是合法的JSON串
     *
     * @param input 要验证的字符串
     * @return true-合法 ，false-非法
     */
    public boolean validate(String input) {
      input = input.trim();
      boolean ret = valid(input);
      return ret;
    }

    private boolean valid(String input) {
      if ("".equals(input)) {
        return true;
      }

      boolean ret = true;
      it = new StringCharacterIterator(input);
      c = it.first();
      col = 1;
      if (!value()) {
        ret = error("value", 1);
      } else {
        skipWhiteSpace();
        if (c != CharacterIterator.DONE) {
          ret = error("end", col);
        }
      }

      return ret;
    }

    private boolean value() {
      return literal("true") || literal("false") || literal("null") || string() || number() || object() || array();
    }

    private boolean literal(String text) {
      CharacterIterator ci = new StringCharacterIterator(text);
      char t = ci.first();
      if (c != t) {
        return false;
      }

      int start = col;
      boolean ret = true;
      for (t = ci.next(); t != CharacterIterator.DONE; t = ci.next()) {
        if (t != nextCharacter()) {
          ret = false;
          break;
        }
      }
      nextCharacter();
      if (!ret) {
        error("literal " + text, start);
      }
      return ret;
    }

    private boolean array() {
      return aggregate('[', ']', false);
    }

    private boolean object() {
      return aggregate('{', '}', true);
    }

    private boolean aggregate(char entryCharacter, char exitCharacter, boolean prefix) {
      if (c != entryCharacter) {
        return false;
      }
      nextCharacter();
      skipWhiteSpace();
      if (c == exitCharacter) {
        nextCharacter();
        return true;
      }

      for (; ; ) {
        if (prefix) {
          int start = col;
          if (!string()) {
            return error("string", start);
          }
          skipWhiteSpace();
          if (c != ':') {
            return error("colon", col);
          }
          nextCharacter();
          skipWhiteSpace();
        }
        if (value()) {
          skipWhiteSpace();
          if (c == ',') {
            nextCharacter();
          } else if (c == exitCharacter) {
            break;
          } else {
            return error("comma or " + exitCharacter, col);
          }
        } else {
          return error("value", col);
        }
        skipWhiteSpace();
      }

      nextCharacter();
      return true;
    }

    private boolean number() {
      if (!Character.isDigit(c) && c != '-') {
        return false;
      }
      int start = col;
      if (c == '-') {
        nextCharacter();
      }
      if (c == '0') {
        nextCharacter();
      } else if (Character.isDigit(c)) {
        while (Character.isDigit(c))
          nextCharacter();
      } else {
        return error("number", start);
      }
      if (c == '.') {
        nextCharacter();
        if (Character.isDigit(c)) {
          while (Character.isDigit(c))
            nextCharacter();
        } else {
          return error("number", start);
        }
      }
      if (c == 'e' || c == 'E') {
        nextCharacter();
        if (c == '+' || c == '-') {
          nextCharacter();
        }
        if (Character.isDigit(c)) {
          while (Character.isDigit(c))
            nextCharacter();
        } else {
          return error("number", start);
        }
      }
      return true;
    }

    private boolean string() {
      if (c != '"') {
        return false;
      }

      int start = col;
      boolean escaped = false;
      for (nextCharacter(); c != CharacterIterator.DONE; nextCharacter()) {
        if (!escaped && c == '\\') {
          escaped = true;
        } else if (escaped) {
          if (!escape()) {
            return false;
          }
          escaped = false;
        } else if (c == '"') {
          nextCharacter();
          return true;
        }
      }
      return error("quoted string", start);
    }

    private boolean escape() {
      int start = col - 1;
      if (" \\\"/bfnrtu".indexOf(c) < 0) {
        return error("escape sequence  \\\",\\\\,\\/,\\b,\\f,\\n,\\r,\\t  or  \\uxxxx ", start);
      }
      if (c == 'u') {
        if (!ishex(nextCharacter()) || !ishex(nextCharacter()) || !ishex(nextCharacter())
            || !ishex(nextCharacter())) {
          return error("unicode escape sequence  \\uxxxx ", start);
        }
      }
      return true;
    }

    private boolean ishex(char d) {
      return "0123456789abcdefABCDEF".indexOf(c) >= 0;
    }

    private char nextCharacter() {
      c = it.next();
      ++col;
      return c;
    }

    private void skipWhiteSpace() {
      while (Character.isWhitespace(c)) {
        nextCharacter();
      }
    }

    private boolean error(String type, int col) {
      System.out.printf("type: %s, col: %s%s", type, col, System.getProperty("line.separator"));
      return false;
    }
  }
}
