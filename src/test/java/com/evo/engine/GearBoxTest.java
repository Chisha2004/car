package com.evo.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class GearBoxTest {

    GearBox gearBox;
    @BeforeEach
    public void setUp() {
        gearBox = new DefaultGearBox();
    }

    @Test
    public void shiftingGears(){

        Assert.isTrue(GearNumber.NEUTRAL.equals(gearBox.getCurrentGear()), "Should be in neutral at start");
        gearBox.shiftUp();
        Assert.isTrue(GearNumber.ONE.equals(gearBox.getCurrentGear()), "Should be in gear one after shift up");
        gearBox.shiftUp();
        Assert.isTrue(GearNumber.TWO.equals(gearBox.getCurrentGear()), "Should be in gear two after shift up");
        gearBox.shiftUp();
        Assert.isTrue(GearNumber.THREE.equals(gearBox.getCurrentGear()), "Should be in gear three after shift up");
        gearBox.shiftUp();
        Assert.isTrue(GearNumber.FOUR.equals(gearBox.getCurrentGear()), "Should be in gear four after shift up");
        gearBox.shiftUp();
        Assert.isTrue(GearNumber.FIVE.equals(gearBox.getCurrentGear()), "Should be in gear five after shift up");

        //Test shifting down
        gearBox.shiftDown();
        Assert.isTrue(GearNumber.FOUR.equals(gearBox.getCurrentGear()), "Should be in gear four after shift down");
        gearBox.shiftDown();
        Assert.isTrue(GearNumber.THREE.equals(gearBox.getCurrentGear()), "Should be in gear three after shift down");
        gearBox.shiftDown();
        Assert.isTrue(GearNumber.TWO.equals(gearBox.getCurrentGear()), "Should be in gear two after shift down");
        gearBox.shiftDown();
        Assert.isTrue(GearNumber.ONE.equals(gearBox.getCurrentGear()), "Should be in gear five after shift down");
        gearBox.shiftDown();
        Assert.isTrue(GearNumber.NEUTRAL.equals(gearBox.getCurrentGear()), "Should be in neutral after shift down");

        //when in neutral we cannot shift any lower
        gearBox.shiftDown();
        gearBox.shiftDown();
        gearBox.shiftDown();
        gearBox.shiftDown();
        Assert.isTrue(GearNumber.NEUTRAL.equals(gearBox.getCurrentGear()), "Should be in neutral after shift down");

    }

    @Test
    public void testGearBasedSpeed(){
        int currentSpeed = 0;

        //default in neutral we have 0 speed
        currentSpeed += gearBox.getCurrentSpeedIncremental();
        Assert.isTrue(currentSpeed == 0, "In neutral speed stays 0");

        //Test gear one max speed
        gearBox.shiftUp();
        assertGearSpeedBellowOrEqualToMaxSpeed();

        gearBox.shiftUp(); //gear two
        assertGearSpeedBellowOrEqualToMaxSpeed();

        gearBox.shiftUp(); //gear three
        assertGearSpeedBellowOrEqualToMaxSpeed();

        gearBox.shiftUp(); //gear four
        assertGearSpeedBellowOrEqualToMaxSpeed();

        gearBox.shiftUp(); //gear five
        assertGearSpeedBellowOrEqualToMaxSpeed();

    }

    private void assertGearSpeedBellowOrEqualToMaxSpeed(){

        if(gearBox.getCurrentGear().equals(GearNumber.ONE)){

            for(int i=0; i < 25; i++){
                gearBox.accelerate();
            }

            Assert.isTrue(gearBox.getCurrentSpeed() <= gearBox.getGetCurrentGearMaxSpeed() ,
                    "We should not go above gear max speed");

        }
    }
}
