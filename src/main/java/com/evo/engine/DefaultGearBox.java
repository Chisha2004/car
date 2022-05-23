package com.evo.engine;

import com.evo.engine.GearBox;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public class DefaultGearBox extends GearBox {

    public DefaultGearBox(){

        addGear(GearNumber.NEUTRAL, 0, 0);
        addGear(GearNumber.ONE,5,16);
        addGear(GearNumber.TWO,7,32);
        addGear(GearNumber.THREE,9,48);
        addGear(GearNumber.FOUR,11,64);
        addGear(GearNumber.FIVE,15,200);

    }
}
