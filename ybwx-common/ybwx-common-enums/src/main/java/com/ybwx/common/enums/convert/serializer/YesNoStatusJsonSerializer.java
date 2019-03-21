package com.ybwx.common.enums.convert.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ybwx.common.enums.YesNoStatus;

import java.io.IOException;


public class YesNoStatusJsonSerializer extends JsonSerializer<YesNoStatus> {

    @Override
    public void serialize(YesNoStatus yesNoStatus, JsonGenerator jsonGenerator, SerializerProvider
            serializerProvider) throws IOException {
        jsonGenerator.writeNumber(yesNoStatus.getValue());
    }
}
