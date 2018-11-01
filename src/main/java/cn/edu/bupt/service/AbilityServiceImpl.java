package cn.edu.bupt.service;

import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.common.model.DeviceType;
import cn.edu.bupt.common.model.Manufacturer;
import cn.edu.bupt.common.model.Model;
import cn.edu.bupt.mapper.AbilityMapper;
import cn.edu.bupt.mapper.DeviceTypeMapper;
import cn.edu.bupt.mapper.ManufacturerMapper;
import cn.edu.bupt.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */


@Service
public class AbilityServiceImpl implements AbilityService{
    @Autowired
    AbilityMapper abilityMapper;

    @Autowired
    ManufacturerMapper manufacturerMapper;

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public void addAbilityToAbilityGroup(Ability ability) {
        abilityMapper.insert(ability);
    }

    @Override
    @Transactional
    public void updateAbility(Ability ability) {
        abilityMapper.updateAbilityDes(ability.getAbilityId(), ability.getAbilityDes());
    }

    @Override
    public void deleteAbilityFromAbilityGroup(int abilityId) {
        abilityMapper.deleteByAbilityId(abilityId);
    }

    @Override
    public List<Ability> findAbilitiesByModelId(int modelId) {
        return abilityMapper.findAllAbilityByModelId(modelId);
    }

    @Override
    public List<Ability> findAbilitiesByThreeCouple(String mName, String dName, String model) {
        Manufacturer m = manufacturerMapper.findManufactureByName(mName);
        if(m==null) return null;
        DeviceType d = deviceTypeMapper.findByMidAndName(m.getManufacturerId(),dName);
        if(d==null) return null;
        Model mo = modelMapper.findByMIdAndDIdAndName(m.getManufacturerId(),d.getDeviceTypeId(),model);
        if(mo==null)return null;
        return findAbilitiesByModelId(mo.getModelId());
    }
    @Override
    public List<Ability> findAllAbilityByDeviceTypeId(int deviceTypeId) {
        return abilityMapper.findAllAbilityByDeviceTypeId(deviceTypeId);
    }

    @Override
    public List<Ability> findAllAbilityByDeviceTypeName(String deviceTypeName) {
       // System.out.println("impl" + deviceTypeName);
        return abilityMapper.findAllAbilityByDeviceTypeName(deviceTypeName);
    }
}
