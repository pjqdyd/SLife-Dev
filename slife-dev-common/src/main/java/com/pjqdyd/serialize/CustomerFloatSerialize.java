package com.pjqdyd.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**   
 * @Description:  [自定义json序列化Float类型]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class CustomerFloatSerialize extends JsonSerializer<Float> {

    //保留一位小数
    private DecimalFormat df = new DecimalFormat("0.0");

    @Override
    public void serialize(Float arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
        if(arg0 != null) {
            arg1.writeString(df.format(arg0));
        }
    }
}
