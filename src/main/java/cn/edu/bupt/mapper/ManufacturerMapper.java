package cn.edu.bupt.mapper;

import cn.edu.bupt.common.model.Manufacturer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
@Mapper
public interface ManufacturerMapper {

    @Select("select  manufacturer_id as manufacturerId, manufacturer_name as  manufacturerName  from manufacturer where manufacturer_name = #{name}")
    Manufacturer findManufactureByName(@Param("name") String name);

    @Insert("insert into manufacturer (manufacturer_name) values (#{manufacturerName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "manufacturerId")
    int insert(Manufacturer manufacturer);

    @Select("select  manufacturer_id as manufacturerId, manufacturer_name as  manufacturerName  from manufacturer where manufacturer_id = #{id}")
    Manufacturer findManufacturerById(@Param("id") int id);

    @Select("select  manufacturer_id as manufacturerId, manufacturer_name as  manufacturerName  from manufacturer")
    List<Manufacturer> findAll();

    @Select("select  manufacturer_id as manufacturerId, manufacturer_name as  manufacturerName  from manufacturer where manufacturer_name like CONCAT(CONCAT('%', #{name}), '%')")
    List<Manufacturer> findByKeyWord(String name);
}
