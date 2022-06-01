package com.evo.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public abstract class VehicleEngine {

    protected Timer accelerateTimer;
    protected Timer decelerateTimer;
    protected GearBox gearBox;
    protected boolean engineRunning = false; // state of the engine
    //FIXME: when a car is in lower gears the incremental speed should be slower.
    // We may need to introduce the property at gear level which indicates the speed of timer based on the gear.
    // Doing that as well
    protected int throttleSpeedIncrementalTimeInterval = 500; // milliseconds
    protected int throttleSpeedDecrementalTimeInterval = 200; // milliseconds default maybe twice as much as the
    protected List<Integer> raves = new ArrayList<>(); //holds each acceleration or in this context rave to add
    protected int previousRaveCount = 0;
    private boolean acceleratorPressed = false;

    public void startEngine(){

        if(engineRunning){
            return;
        }

        raves = new ArrayList<>();

        engineRunning = true;
    }

    public void stopEngine() {

        gearBox.stop();

        raves.clear();

        engineRunning = false;
    }

    public boolean isEngineRunning(){
        return engineRunning;
    }

    public void shiftGearUp(){
        gearBox.shiftUp();
        raves.clear();
    }

    public void setGearBox(GearBox gearBox){
        this.gearBox = gearBox;
    }

    protected void addRaveCount(){
        //We do not need that many events to prove that acceleration is still going on 10 should be sufficient
        if(raves.size() < 10) {
            raves.add(1);
        }
    }

    public void pressAccelerator(){
        if(isEngineRunning()) {
            acceleratorPressed = true;
        }
    }

    public void releaseAccelerator(){
        acceleratorPressed = false;
    }

    public void shiftGearDown() {
        gearBox.shiftDown();
    }

    public GearNumber getCurrentGearNumber() {
        return gearBox.currentGearNumber;
    }

    public int getCurrentSpeed(){
        return gearBox.getCurrentSpeed();
    }

    public void updateSpeed(){
        if(acceleratorPressed){
            addRaveCount();

        }else{
            //decelerate
            if(!raves.isEmpty()){
                raves.remove(0);
            }else{
                gearBox.decelerate();
            }
        }

        if(!raves.isEmpty() && raves.size() >= previousRaveCount){
            gearBox.accelerate();
            previousRaveCount = raves.size();
        }else if(!raves.isEmpty() && raves.size() < previousRaveCount){
            gearBox.decelerate();
            previousRaveCount = raves.size();
        }

    }


}
