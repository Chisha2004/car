package com.evo.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GearBox {

    protected GearNumber currentGearNumber = GearNumber.NEUTRAL;
    private Map<GearNumber, Integer> maxGearSpeed = new HashMap<>();
    private Map<GearNumber, Integer> gearSpeedIncremental = new HashMap<>();
    protected List<GearNumber> gearNumberNames = new ArrayList<>();
    private int currentGearNumberIndex = 0; //default should be neutral
    private int currentSpeed = 0;

    public int getCurrentSpeedIncremental(){
        return gearSpeedIncremental.get(currentGearNumber);
    }

    /**
     * Order off adding
     * */
    protected void addGear(GearNumber gearNumber, int gearIncremental, int gearMaxSpeed) {

        gearSpeedIncremental.put(gearNumber, gearIncremental);
        maxGearSpeed.put(gearNumber, gearMaxSpeed);
        gearNumberNames.add(gearNumber);
    }

    private void shiftTo(int gearNumberIndex){
        currentGearNumber = gearNumberNames.get(gearNumberIndex);
        currentGearNumberIndex = gearNumberIndex;
    }

    public void shiftUp(){
        if(currentGearNumberIndex < gearNumberNames.size() - 1){
           shiftTo(currentGearNumberIndex + 1);
        }
    }

    public void shiftDown(){
        if(currentGearNumberIndex > 0){
            shiftTo(currentGearNumberIndex - 1);
        }
    }

    public void shiftToNeutral(){
        shiftTo(0);
    }

    public GearNumber getCurrentGear(){
        return currentGearNumber;
    }

    public int getGetCurrentGearMaxSpeed() {
        return maxGearSpeed.get(currentGearNumber);
    }

    public void accelerate() {
        if(currentSpeed < getGetCurrentGearMaxSpeed()){
            currentSpeed += getCurrentSpeedIncremental();
        }

        if(currentSpeed > getGetCurrentGearMaxSpeed()){
            currentSpeed = getGetCurrentGearMaxSpeed();
        }
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void decelerate() {

        if(currentSpeed > 0){
            currentSpeed -= getCurrentSpeedIncremental();
        }

        if(currentSpeed < 0){
            currentSpeed = 0;
        }
    }

    public void stop() {
        currentSpeed = 0;
        currentGearNumber = GearNumber.NEUTRAL;
        currentGearNumberIndex = 0;
    }
}
