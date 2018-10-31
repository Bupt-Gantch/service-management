package cn.edu.bupt.service;

import cn.edu.bupt.common.model.Ability;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */
public interface AbilityService {
    public void addAbilityToAbilityGroup(Ability ability);
    public void updateAbility(Ability ability);
    public void deleteAbilityFromAbilityGroup(int abilityId);
    public List<Ability> findAbilitiesByModelId(int modelId);
    public List<Ability> findAbilitiesByThreeCouple(String mName, String dName, String model);
}
