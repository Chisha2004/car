package com.evo.engine;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Timer;
import java.util.TimerTask;

public class DefaultVehicleEngine extends VehicleEngine {

    @Resource
    private GearBox gearBox;

    //FIXME: in future we can split gears from engine for now we put everything


    public GearNumber getGearNumber(){
        return gearBox.getCurrentGear();
    }

    public int getSpeed(){
        return gearBox.getCurrentSpeed();
    }

    public int getMaxSpeed(){
        return gearBox.getGetCurrentGearMaxSpeed();
    }

}
