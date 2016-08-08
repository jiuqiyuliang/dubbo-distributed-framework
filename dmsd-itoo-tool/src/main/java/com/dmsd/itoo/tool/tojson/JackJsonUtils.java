package com.dmsd.itoo.tool.tojson;

import org.codehaus.jackson.map.ObjectMapper;

public class JackJsonUtils {


    public ObjectMapper objectMapper = null;


    /**
     * 将对象转换为json字符串
     *
     * @param obj 需要转换的对象（可以为list）
     * @throws Exception 异常处理
     */
    public String BeanToJson(Object obj) throws Exception {
        objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
