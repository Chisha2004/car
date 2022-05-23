package com.evo.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class VehicleEngine {

    protected Timer accelerateTimer;
    protected Timer decelerateTimer;
    protected GearBox gearBox;
    protected boolean engineRunning = false; // state of the engine
    protected int throttleSpeedIncrementalTimeInterval = 100; // milliseconds
    protected int throttleSpeedDecrementalTimeInterval = 200; // milliseconds default maybe twice as much as the
    protected List<Integer> raves; //holds each acceleration or in this context rave to add

    public void startEngine(){

        if(engineRunning){
            return;
        }

        raves = new ArrayList<>();

        accelerateTimer = new Timer();
        decelerateTimer = new Timer();

        accelerateTimer.scheduleAtFixedRate( new DefaultVehicleEngine.AccelerateTimerTask(),0, throttleSpeedIncrementalTimeInterval);
        decelerateTimer.scheduleAtFixedRate( new DefaultVehicleEngine.DecelerateTimerTask(), 0,throttleSpeedDecrementalTimeInterval);

        engineRunning = true;
    }

    public void stopEngine() {

        gearBox.stop();

        if(accelerateTimer != null){
            accelerateTimer.cancel();
        }
        if(decelerateTimer != null){
            decelerateTimer.cancel();
        }

        raves.clear();

        engineRunning = false;
    }

    public boolean isEngineRunning(){
        return engineRunning;
    }

    public void shiftGearUp(){
        gearBox.shiftUp();
    }

    public void setGearBox(GearBox gearBox){
        this.gearBox = gearBox;
    }

    protected void addAccelerationEvent(){
        //We do need that many events to prove that acceleration is still going on 10 should be sufficient
        if(raves.size() < 10) {
            raves.add(1);
        }
    }

    public void accelerate(){
       if(isEngineRunning()){
           addAccelerationEvent();
       }
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

    protected class AccelerateTimerTask extends TimerTask {

        @Override
        public void run() {
            if(!raves.isEmpty()){

                gearBox.accelerate();
                //assuming we have processed we remove from the queue
                raves.remove(0);
            }
        }
    }

    protected class DecelerateTimerTask extends TimerTask {

        @Override
        public void run() {
            if(raves.isEmpty() && gearBox.getCurrentSpeed() > 0){
                gearBox.decelerate();
            }
        }
    }


}
