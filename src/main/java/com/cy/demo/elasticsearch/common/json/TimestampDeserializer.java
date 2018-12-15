package com.cy.demo.elasticsearch.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: caoyoung
 * @Description: json反序列化时用于将string转成long
 * @Date: 2018-06-08 09:36
 * @Modified By:
 * @Test By:
 */
@Component
public class TimestampDeserializer extends JsonDeserializer<Date> {
    private static final Logger logger = LoggerFactory.getLogger(TimestampDeserializer.class);
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getText();
        try {
            return value == null ? null : new Date(Long.valueOf(value));
        } catch (NumberFormatException e) {
            logger.error("解析长整形错误", e);
            return null;
        }
    }
}
