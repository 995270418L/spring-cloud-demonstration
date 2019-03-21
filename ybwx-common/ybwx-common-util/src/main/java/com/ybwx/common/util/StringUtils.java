package com.ybwx.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class StringUtils {

    public static String InputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) != -1){
            arrayOutputStream.write(buffer, 0, length);
        }
        String str = arrayOutputStream.toString(StandardCharsets.UTF_8.name());
        return str;
    }
}
