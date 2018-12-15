package com.cy.demo.elasticsearch.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: caoyoung
 * @Description: json序列化时用于将long转成string，java端long型值传到前端会失去精度
 * @Date: 2018-06-08 09:36
 * @Modified By:
 * @Test By:
 */
@Component
public class LongJsonSerializer  extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String text = (value == null ? null : String.valueOf(value));
        if (text != null) {
            jsonGenerator.writeString(text);
        }
    }
}
