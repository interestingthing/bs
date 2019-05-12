package personal.bs.utils;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author: chenjingang@gauzi.com  2019/5/12 17:59
 */
public class Array2StringSerializer extends JsonSerializer<Object[]> {
    @Override
    public void serialize(Object[] objects, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

jsonGenerator.writeString(objects.toString());
    }
}
