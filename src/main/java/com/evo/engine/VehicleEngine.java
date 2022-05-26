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
    protected List<Integer> raves; //holds each acceleration or in this context rave to add
    private boolean acceleratorPressed = false;

    public void startEngine(){

        if(engineRunning){
            return;
        }

        raves = new ArrayList<>();

        accelerateTimer = new Timer(throttleSpeedIncrementalTimeInterval, new AccelerateTimerListener() );
        decelerateTimer = new Timer(throttleSpeedDecrementalTimeInterval, new DecelerateTimerListener());

        accelerateTimer.start();
        decelerateTimer.start();
        engineRunning = true;
    }

    public void stopEngine() {

        gearBox.stop();

        if(accelerateTimer != null){
            accelerateTimer.stop();
        }
        if(decelerateTimer != null){
            decelerateTimer.stop();
        }

        raves.clear();

        engineRunning = false;
    }

    public boolean isEngineRunning(){
        return engineRunning;
    }

    public void shiftGearUp(){
        gearBox.shiftUp();
        accelerateTimer.setDelay(gearBox.getCurrentSpeedTimerDelay());
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

    protected class AccelerateTimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(acceleratorPressed){
                addRaveCount();
            }

            if(!raves.isEmpty()){

                gearBox.accelerate();
                //assuming we have processed we remove from the queue
                raves.remove(0);
            }
        }
    }

    protected class DecelerateTimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(acceleratorPressed){
                return;
            }

            if(raves.isEmpty() && gearBox.getCurrentSpeed() > 0){
                gearBox.decelerate();
            }
        }
    }


}
