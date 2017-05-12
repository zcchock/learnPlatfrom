package com.zc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by chock on 2017/5/11.
 */
public class JsonUtil {
    public static void responseOutWithJson(ServletResponse response,
                                           Object result){
//       JSONObject responseJSONObject = JSONObject.fromObject(result);
        StringWriter str = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(str, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
        System.out.println(str);
        System.out.println();
        System.out.println(str.toString());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(str.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
