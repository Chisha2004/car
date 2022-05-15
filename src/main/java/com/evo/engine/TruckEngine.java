package com.evo.engine;

public abstract class TruckEngine {
    protected GearNumber gearNumber = GearNumber.NEUTRAL; //neutral
    protected int speed = 0; //
    protected int throttleSpeedIncrementalTimeInterval = 100; // milliseconds
    protected int throttleSpeedDecrementalTimeInterval = 200; // milliseconds default maybe twice as much as the

    public abstract GearNumber getGearNumber();
    public abstract int getSpeed();
    public abstract int getMaxSpeed();
    public abstract int getMaxNumberGears();
    public abstract void accelerate();
    public abstract void shiftGearUp();
    public abstract void shiftGearDown();
    public abstract void shiftToNeutral();
    public abstract void stop();
    public abstract void reverse();
    public abstract void startEngine();
    public abstract void stopEngine();

}
