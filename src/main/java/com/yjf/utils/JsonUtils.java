package com.yjf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/9/21 10:37
 * @Description
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * @return java.lang.String
     * @Description 将对象转换成json字符串。
     * @author 余俊锋
     * @date 2020/9/21 10:38
     * @params data
     */
    public static String pojoToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param beanType
     * @return T
     * @Description 将json结果集转化为对象
     * @author 余俊锋
     * @date 2020/9/21 10:38
     * @params jsonData
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param beanType
     * @return java.util.List<T>
     * @Description 将json数据转换成pojo对象list
     * @author 余俊锋
     * @date 2020/9/21 10:38
     * @params jsonData
     */


    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return
     * @Description 响应返回json数据
     * @author 余俊锋
     * @date 2020/9/21 10:37
     * @params 响应   字符串
     */
    public static void responseJSON(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        // response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(object);    //object对象转为 josn 字符串
        OutputStream out = response.getOutputStream();
        out.write(jsonStr.getBytes("UTF-8"));
        out.flush();
    }

    public static Map getQqAccessToken(String url) {
        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            // 获取响应数据(json)
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));
                // return JsonUtils.jsonToPojo(result, HashMap.class);
                return parseGetParams(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map getQqOpenId(String url) {
        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            // 获取响应数据(json)
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));
                Map<String, String> map = parseJSONP(result);
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> parseGetParams(String params) {
        Map<String, String> map = new HashMap<>();
        String[] replace = params.split("&");
        for (String s : replace) {
            String[] split = s.split("=");
            map.put(split[0], split[1]);
        }

        return map;
    }

    private static Map<String,String> parseJSONP(String jsonp) {
        int startIndex = jsonp.indexOf(
                "(");
        int endIndex = jsonp.lastIndexOf(
                ")");
        if (startIndex!=-1&&endIndex!=-1){
            String json = jsonp.substring(startIndex +
                    1, endIndex);
            return JsonUtils.jsonToPojo(json, HashMap.class);
        }
        return JsonUtils.jsonToPojo(jsonp, HashMap.class);
    }


}
