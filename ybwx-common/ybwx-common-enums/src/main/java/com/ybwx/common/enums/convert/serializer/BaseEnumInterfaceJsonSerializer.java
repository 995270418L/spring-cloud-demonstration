package com.ybwx.common.enums.convert.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ybwx.common.enums.BaseEnumInterface;

import java.io.IOException;

/**
 * @描述 :
 * @作者 :	pst
 * @日期 :	2017/10/13
 * @时间 :	17:13
 */
public class BaseEnumInterfaceJsonSerializer extends JsonSerializer<BaseEnumInterface> {

    @Override
    public void serialize(BaseEnumInterface baseEnumInterface, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeRawValue(String.valueOf(baseEnumInterface.getValue()));
        gen.writeFieldName(gen.getOutputContext().getCurrentName() + "_name");
        gen.writeRawValue("\"" + baseEnumInterface.getName() + "\"");
    }

}
