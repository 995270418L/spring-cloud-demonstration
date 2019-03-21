package com.ybwx.common.enums.convert.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ybwx.common.enums.ObjectRelationshipType;

import java.io.IOException;

public class ObjectRelationshipTypeJsonDeserialize extends JsonDeserializer<ObjectRelationshipType> {

    @Override
    public ObjectRelationshipType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Integer value = p.readValueAs(Integer.class);
        return ObjectRelationshipType.get(value);
    }

}
