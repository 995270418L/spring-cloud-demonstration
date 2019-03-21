package com.ybwx.common.enums.convert.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ybwx.common.enums.type.ChargeFrequencyType;

import java.io.IOException;

public class ChargeFrequencyTypeDeserializer extends JsonDeserializer<ChargeFrequencyType> {

    @Override
    public ChargeFrequencyType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return ChargeFrequencyType.get(jsonParser.getIntValue());
    }
}
