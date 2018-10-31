package cn.edu.bupt;

import cn.edu.bupt.mapper.AbilityMapper;
import cn.edu.bupt.mapper.DeviceTypeMapper;
import cn.edu.bupt.mapper.ManufacturerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.mappers.ModelMapper;

/**
 * Created by Administrator on 2018/4/10.
 */
@RunWith(SpringRunner.class)
@EnableTransactionManagement
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private ManufacturerMapper mapper;

    @Autowired
    private DeviceTypeMapper mapper1;

    @Autowired
    private ModelMapper mapper2;

    @Autowired
    private AbilityMapper mapper3;

    @Test
    public void test(){
//        List ls = new ArrayList<>();
//        ls.add(ls);
//        ls.toString();
        System.err.println(123);
//        Manufacturer m =  new Manufacturer();
//        System.out.println("before "+m.getManufacturerId());;
//        m.setManufacturerName("test3");
//        mapper.insert(m);
//        System.out.println("after "+m.getManufacturerId());;
//        DeviceType d = new DeviceType();
//        d.setManufacturerId(2);
//        d.setDeviceTypeName("test1");
//        mapper1.insert(d);
  //      mapper1.findAll(1);
        //System.out.println();
//        Model m = new Model();
//        m.setManufacturerId(1);
//        m.setDeviceTypeId(1);
//        m.setModelName("testModel");

//        Service s = new Service();
//                s.setModelId(1);
//        s.setServiceDes("anather des 1");
//        //mapper3.insert(s);
//        System.out.println(mapper3.findAllServiceByModelId(1));;
//        mapper3.deleteByServiceId(1);
//        mapper2.insert(m);
//        System.out.println(mapper2.findAllByKeyWord(1,1,"edl"));
     //  System.out.println(mapper1.findAllByKeyWord(1,"test1"));
    }
}
