package com.evo.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class VehicleEngineTest {
    private VehicleEngine vehicleEngine;

    @BeforeEach
    public void setUp(){
        vehicleEngine = new DefaultVehicleEngine();
        vehicleEngine.setGearBox(new DefaultGearBox());
    }

    @Test
    public void testStartEngine(){
        Assert.isTrue(vehicleEngine.isEngineRunning() == false, "Engine should not be running");

        vehicleEngine.startEngine();

        Assert.isTrue(vehicleEngine.isEngineRunning(), "Engine should be running after engine start");
    }
}
