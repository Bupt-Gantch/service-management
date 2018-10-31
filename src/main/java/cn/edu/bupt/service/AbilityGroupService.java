package cn.edu.bupt.service;

import cn.edu.bupt.common.model.AbilityGroup;
import cn.edu.bupt.common.model.DeviceType;
import cn.edu.bupt.common.model.Manufacturer;
import cn.edu.bupt.common.model.Model;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */
public interface AbilityGroupService {
    public void addAbilityGroup(AbilityGroup abilityGroup);
    public void deleteAbilityGroup(int modelId);
    public List<AbilityGroup> getAllAbilityGroup();
    public List<Manufacturer> getManufacturersByKeyWords(String keyWords);
    public List<DeviceType> getDeviceTypesByKeyWords(int mid, String keyWords);
    public List<Model> getModelsByKeyWords(int mid, int did, String keyWords);
}
