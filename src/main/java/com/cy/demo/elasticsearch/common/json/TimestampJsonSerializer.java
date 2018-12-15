package com.cy.demo.elasticsearch.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: caoyoung
 * @Description: json序列化时用于将long转成string，java端long型值传到前端会失去精度
 * @Date: 2018-06-08 09:36
 * @Modified By:
 * @Test By:
 */
@Component
public class TimestampJsonSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            Long timestamp = value==null?null:value.getTime();
            jsonGenerator.writeString(String.valueOf(timestamp));
    }
}
