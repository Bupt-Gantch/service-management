package cn.edu.bupt.common.util;

import com.google.gson.JsonParser;

/**
 * Created by Administrator on 2018/4/10.
 */
public class SimpleTest {
    public static void main(String[] args){
        String str = "{\"test\":1 }";
        new JsonParser().parse(str).getAsJsonObject().get("test").getAsString();
       // BufferedReader
    }
}
