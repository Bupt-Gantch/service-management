package cn.edu.bupt.controller;

import cn.edu.bupt.common.model.AbilityGroup;
import cn.edu.bupt.common.model.DeviceType;
import cn.edu.bupt.common.model.Manufacturer;
import cn.edu.bupt.common.model.Model;
import cn.edu.bupt.service.AbilityGroupService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@RestController
@RequestMapping("/api/v1/servicemanagement")
@Slf4j
public class AbilityGroupController extends BaseController{

    @Autowired
    AbilityGroupService abilityGroupService;

    JsonParser parser = new JsonParser();

    //@PreAuthorize("#oauth2.hasScope('all') OR hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/abilityGroup", method = RequestMethod.POST)
    public AbilityGroup  saveAbilityGroup(@RequestBody String group,HttpServletResponse response){
        log.info("AbilityGroup.saveAbilityGroup receive request [{}]",group);
        JsonObject obj = parser.parse(group).getAsJsonObject();
        String manufacturerName = obj.has("manufacturerName")?obj.get("manufacturerName").getAsString():null;
        String deviceType = obj.has("deviceType")?obj.get("deviceType").getAsString():null;
        String model = obj.has("model")?obj.get("model").getAsString():null;
        if(manufacturerName==null||deviceType==null||model==null){
            return null;
        }
        AbilityGroup abilityGroup = new AbilityGroup(manufacturerName,deviceType,model);
        abilityGroupService.addAbilityGroup(abilityGroup);
        return abilityGroup;
    }

    //@PreAuthorize("#oauth2.hasScope('all') OR hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/abilityGroup", method = RequestMethod.GET)
    public List<AbilityGroup> getAllAbilityGroups(){
        log.info("AbilityGroup.getAllAbilityGroups receive request [{}]");
        return abilityGroupService.getAllAbilityGroup();
    }

    //@PreAuthorize("#oauth2.hasScope('all') OR hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/abilityGroup", method = RequestMethod.DELETE)
    public void deleteAbilityGroup(@RequestParam int modelId){
        log.info("AbilityGroup.deleteAbilityGroup receive request [{}]",modelId);
        abilityGroupService.deleteAbilityGroup(modelId);
    }

    //@PreAuthorize("#oauth2.hasScope('all') OR hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/abilityGroup/manufacturers", method = RequestMethod.GET)
    public List<Manufacturer> getManufacturers(@RequestParam(required = false) String keyword,
                                               HttpServletResponse response){
        log.info("AbilityGroup.getManufacturers receive request [{}]",keyword);
        return abilityGroupService.getManufacturersByKeyWords(keyword);
    }

    //@PreAuthorize("#oauth2.hasScope('all') OR hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/abilityGroup/deviceTypes", method = RequestMethod.GET)
    public List<DeviceType> getDeviceTypes(@RequestParam int manufacturerId, @RequestParam(required = false) String keyword,
                                           HttpServletResponse response){
        log.info("AbilityGroup.getDeviceTypes receive request [{}] [{}]",manufacturerId,keyword);
        return abilityGroupService.getDeviceTypesByKeyWords(manufacturerId,keyword);
    }

    //@PreAuthorize("#oauth2.hasScope('all') OR hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
    @RequestMapping(value = "/abilityGroup/models", method = RequestMethod.GET)
    public List<Model> getModels(@RequestParam int manufacturerId, @RequestParam int deviceTypeId, @RequestParam(required = false)  String keyword,
                                 HttpServletResponse response){
        log.info("AbilityGroup.getModels receive request [{}] [{}] [{}]",manufacturerId,deviceTypeId,keyword);
        return abilityGroupService.getModelsByKeyWords(manufacturerId,deviceTypeId,keyword);
    }
}
