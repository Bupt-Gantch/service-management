package cn.edu.bupt.mapper;

import cn.edu.bupt.common.model.Ability;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Mapper
public interface AbilityMapper {
    @Insert("insert into ability (model_id,ability_des) values (#{modelId},#{abilityDes}) ")
    @Options(useGeneratedKeys = true, keyProperty = "abilityId")
    void insert(Ability ability);

    @Delete("delete from ability where ability_id=#{abilityId}")
    void deleteByAbilityId(int abilityId);


    @Update("update ability set service_des=#{abilityDes} where ability_id=#{abilityId}")
    public void updateAbilityDes(@Param("abilityId") int abilityId, @Param("abilityDes") String abilityDes);

    @Select("select  ability_id  as abilityId,model_id as  modelId,ability_des as abilityDes  from ability where model_id = #{id}")
    List<Ability> findAllAbilityByModelId(int id);

    @Delete("delete from ability where model_id = #{modelId}")
    void delete(int modelId);

    @Select("select distinct ability_des as abilityDes " +
            "from ability, device_type, model " +
            "where device_type.device_type_id = #{deviceTypeId} " +
            "and device_type.device_type_id = model.device_type_id " +
            "and model.model_id = ability.model_id")
    List<Ability> findAllAbilityByDeviceTypeId(int deviceTypeId);
//    "select distinct 1  as abilityId, " +
//            "1 as modelId, " +
    @Select("select distinct ability_des as abilityDes " +
            "from ability, device_type, model " +
            "where device_type_name like concat('%',#{deviceTypeName},'%') " +
            "and device_type.device_type_id = model.device_type_id " +
            "and model.model_id = ability.model_id")
    List<Ability> findAllAbilityByDeviceTypeName(String deviceTypeName);
}
