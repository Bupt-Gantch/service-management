package cn.edu.bupt.common.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Administrator on 2018/4/27.
 */
public class AbilityValidator {
    private static JsonParser parser = new JsonParser();
    public static boolean isValidateAbility(String serviceDes){
        try{
            JsonObject serviceJson = parser.parse(serviceDes).getAsJsonObject();
            if(!serviceJson.has("serviceName")||!serviceJson.getAsJsonPrimitive("serviceName").isString()) return false;
            if(!serviceJson.has("serviceDescription")||!serviceJson.getAsJsonPrimitive("serviceDescription").isString()) return false;
            if(!serviceJson.has("serviceType")||!serviceJson.getAsJsonPrimitive("serviceType").isString()) return false;

            if(serviceJson.get("serviceType").equals("thirdparty")){
                if(!serviceJson.has("protocol")||!serviceJson.getAsJsonPrimitive("protocol").isString()) return false;
                if(!serviceJson.has("url")||!serviceJson.getAsJsonPrimitive("url").isString()) return false;
            }
            if(!serviceJson.has("requireResponse")||!serviceJson.getAsJsonPrimitive("requireResponse").isBoolean()) return false;
            if(!serviceJson.has("serviceBody")||!serviceJson.get("serviceBody").isJsonObject()){
                return false;
            }else{
                JsonObject serviceBody = serviceJson.getAsJsonObject("serviceBody");
                if(!serviceBody.has("methodName")||!serviceBody.getAsJsonPrimitive("methodName").isString()) return false;
                if(!serviceBody.has("params")||!serviceBody.get("params").isJsonArray()) return false;

                for(JsonElement ele:serviceBody.getAsJsonArray("params")){
                    if(!ele.isJsonObject()) return false;
                    if(!ele.getAsJsonObject().has("key")||!ele.getAsJsonObject().getAsJsonPrimitive("key").isString()) return false;
                    if(!ele.getAsJsonObject().has("type")||!ele.getAsJsonObject().getAsJsonPrimitive("type").isNumber()) return false;
                    if(!ele.getAsJsonObject().has("value")||!ele.getAsJsonObject().getAsJsonPrimitive("value").isString()) return false;
                }
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
