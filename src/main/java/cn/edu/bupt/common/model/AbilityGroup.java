package cn.edu.bupt.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AbilityGroup {
    @Getter @Setter
    private Manufacturer manufacturer;
    @Getter @Setter
    private DeviceType deviceType;
    @Getter @Setter
    private Model model;
    public AbilityGroup(String manufacturerName, String deviceTypeName, String modelName){
        this.manufacturer = new Manufacturer();
        manufacturer.setManufacturerName(manufacturerName);
        this.deviceType = new DeviceType();
        deviceType.setDeviceTypeName(deviceTypeName);
        this.model = new Model();
        model.setModelName(modelName);
    }

    public AbilityGroup(){

    }
}
