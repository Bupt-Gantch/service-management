package cn.edu.bupt.controller;

import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.common.util.AbilityValidator;
import cn.edu.bupt.service.AbilityService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@RestController
@RequestMapping("/api/v1/servicemanagement")
@Slf4j
public class AbilityController extends BaseController{
    @Autowired
    AbilityService abilityService;
    JsonParser parser = new JsonParser();

    @RequestMapping(value = "/ability/device-type-id/{deviceTypeId}", method = RequestMethod.GET)
    public  String findAllAbilityDesByDeviceTypeId(@PathVariable int deviceTypeId){
        log.info("AbilityController.findAbilitiesDesByDeviceId receive a request [{}]" ,deviceTypeId);
        JsonArray res = new JsonArray();
        for (Ability a : abilityService.findAllAbilityByDeviceTypeId(deviceTypeId)){
            res.add(parser.parse(a.getAbilityDes()));
        }
        log.info("AbilityController.findAbilitiesDesByDeviceId return [{}] data", res.size());
        return res.toString();
    }
    @RequestMapping(value = "/ability/device-type-name/{deviceTypeName}", method = RequestMethod.GET)
    public String findAllAbilityDesByDeviceTypeName(@PathVariable String deviceTypeName){
        log.info("AbilityController.findAbilitiesDesByDeviceName receive a request [{}]" ,deviceTypeName);
        JsonArray res = new JsonArray();
        for (Ability a : abilityService.findAllAbilityByDeviceTypeName(deviceTypeName)){
            res.add(parser.parse(a.getAbilityDes()));
        }
        log.info("AbilityController.findAbilitiesDesByDeviceName return [{}] data", res.size());
        return res.toString();
    }

    @RequestMapping(value = "/ability/add-all-ability", method = RequestMethod.POST)
    public List<Ability> saveAllAbility(@RequestBody String data){
        log.info("AbilityController.saveAllAbility receive a request [{}]" ,data);
        JsonObject objs = parser.parse(data).getAsJsonObject();
        int modelId = objs.has("modelId") ?
                objs.getAsJsonPrimitive("modelId").getAsInt() : -1;
        List<Ability> res = new ArrayList<>();

        JsonArray abilityDesArr = objs.getAsJsonArray("abilityDesArr");
        if(modelId == -1 || abilityDesArr == null) return null;

        for (JsonElement e : abilityDesArr){
            String abilityDes = e.getAsJsonObject().toString();
            if(!AbilityValidator.isValidateAbility(abilityDes)) continue;
            Ability ability = new Ability();
            ability.setModelId(modelId);
            ability.setAbilityDes(abilityDes);
            abilityService.addAbilityToAbilityGroup(ability);
            res.add(ability);
        }
        log.info("AbilityController.saveAllAbility save [{}] data" ,abilityDesArr.size());
        return res;
    }

   @RequestMapping(value = "/ability",method = RequestMethod.POST)
   //@PreAuthorize("#oauth2.hasScope('all') OR hasAuthority('TENANT_ADMIN')")
   public Ability saveAbility(@RequestBody String data,HttpServletResponse response){
        log.info("AbilityController.saveAbility receive a request [{}]" ,data );
        JsonObject obj = parser.parse(data).getAsJsonObject();
        int abilityId = obj.has("abilityId")? obj.getAsJsonPrimitive("abilityId").getAsInt():-1;
        int modelId = obj.has("modelId")? obj.getAsJsonPrimitive("modelId").getAsInt():-1;
        String abilityDes = obj.has("abilityDes")?obj.get("abilityDes").toString():null;
        if(modelId==-1||abilityDes==null) return null;
        if(!AbilityValidator.isValidateAbility(abilityDes))return null;
        Ability ability = new Ability();
        ability.setModelId(modelId);
        ability.setAbilityDes(abilityDes);

        if(abilityId==-1){
            abilityService.addAbilityToAbilityGroup(ability);
        }else{
            ability.setAbilityId(abilityId);
            abilityService.updateAbility(ability);
        }
        return ability;
    }

    @RequestMapping(value = "/ability/{abilityId}",method = RequestMethod.DELETE)
    //@PreAuthorize("#oauth2.hasScope('all') OR hasAuthority('TENANT_ADMIN')")
    public void deleteAbility(@PathVariable int abilityId,HttpServletResponse response){
        log.info("AbilityController.deleteAbility receive a request [{}]" ,abilityId );
        abilityService.deleteAbilityFromAbilityGroup(abilityId);
    }

    @RequestMapping(value = "/ability/{modelId}",method = RequestMethod.GET)
    //@PreAuthorize("#oauth2.hasScope('all') OR hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    public List<Ability> findAbilitiesByModelId(@PathVariable int modelId,HttpServletResponse response){
        log.info("AbilityController.findAbilitiesByModelId receive a request [{}]" ,modelId );
       return abilityService.findAbilitiesByModelId(modelId);
    }

    @RequestMapping(value = "/ability/{manufacturerName}/{deviceTypeName}/{modelName:.+}",method = RequestMethod.GET)
    //@PreAuthorize("#oauth2.hasScope('all') OR hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    public List<Ability> findAbilitiesByThreeTouple(@PathVariable String manufacturerName,@PathVariable String deviceTypeName,
                                                    @PathVariable String modelName, HttpServletResponse response){
        log.info("AbilityController.findAbilitiesByModelId receive a request [{},{},{}]" ,manufacturerName,deviceTypeName ,modelName);
        return abilityService.findAbilitiesByThreeCouple(manufacturerName,deviceTypeName,modelName);
    }

}
