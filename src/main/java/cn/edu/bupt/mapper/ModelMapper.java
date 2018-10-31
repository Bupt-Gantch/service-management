package cn.edu.bupt.mapper;

import cn.edu.bupt.common.model.Model;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Mapper
public interface ModelMapper {
    @Select("select  model_id as modelId,device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,model_name as modelName from model where manufacturer_id = #{id} and   device_type_id=#{deviceTypeId} and model_name =#{name}")
     Model findByMIdAndDIdAndName(@Param("id") int id, @Param("deviceTypeId") int deviceTypeId, @Param("name") String name);

    @Insert("insert into model (manufacturer_id,device_type_id,model_name) values (#{manufacturerId},#{deviceTypeId},#{modelName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "modelId")
    void insert(Model model);

    @Select("select  model_id as modelId,device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,model_name as modelName  from model where manufacturer_id = #{manufacturerId} and device_type_id=#{deviceTypeId}")
    List<Model> findAll(@Param("manufacturerId") int manufacturerId, @Param("deviceTypeId") int deviceTypeId);

    @Select("select  model_id as modelId,device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,model_name as modelName from model where manufacturer_id = #{id} and   device_type_id=#{deviceTypeId} and model_name like CONCAT(CONCAT('%', #{keyWord}), '%')")
    List<Model> findAllByKeyWord(@Param("id") int id, @Param("deviceTypeId") int deviceTypeId, @Param("keyWord") String keyWord);

    @Select("select  model_id as modelId,device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,model_name as modelName,model_id as modelId from model where 1=1 ")
    List<Model> find();

    @Select("select  model_id as modelId,device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,model_name as modelName from model where model_id = #{modelId} ")
    Model findById(int modelId);

    @Delete("delete from model where model_id = #{modelId}")
    void delete(int modelId);
}
