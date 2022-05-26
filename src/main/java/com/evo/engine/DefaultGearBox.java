package com.evo.engine;

import com.evo.engine.GearBox;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public class DefaultGearBox extends GearBox {

    public DefaultGearBox(){

        //FIXME: we may just need an incremental interval value of 1 rather have different ones per gear
        addGear(GearNumber.NEUTRAL, 0, 0);
        addGear(GearNumber.ONE,130,16);
        addGear(GearNumber.TWO,135,32);
        addGear(GearNumber.THREE,140,48);
        addGear(GearNumber.FOUR,145,70);
        addGear(GearNumber.FIVE,150,200);

    }
}
