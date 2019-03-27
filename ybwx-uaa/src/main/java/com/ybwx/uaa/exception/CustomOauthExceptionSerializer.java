package com.ybwx.uaa.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauth2Exception> {

    protected CustomOauthExceptionSerializer() {
        super(CustomOauth2Exception.class);
    }

    @Override
    public void serialize(CustomOauth2Exception value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", value.getHttpErrorCode());
        jsonGenerator.writeObjectField("data", null);
        jsonGenerator.writeObjectField("message", value.getMessage());
        if (value.getAdditionalInformation()!=null) {
            log.info("exception addtional infomation: {}", value.getAdditionalInformation());
//            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
//                String key = entry.getKey();
//                String add = entry.getValue();
////                jsonGenerator.writeStringField(key, add);
//            }
        }
        jsonGenerator.writeEndObject();
    }
}
