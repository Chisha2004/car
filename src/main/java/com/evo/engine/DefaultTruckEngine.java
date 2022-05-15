package com.evo.engine;

import com.evo.engine.TruckEngine;

import javax.annotation.Resource;
import java.util.Timer;
import java.util.TimerTask;

public class DefaultTruckEngine extends TruckEngine {

    private static int MAX_SPEED = 200; //km
    private static int MAX_NUMBER_GEARS = 5;
    private Timer accelerateTimer;
    private Timer decelerateTimer;

    @Resource
    private GearBox gearBox;

    //FIXME: in future we can split gears from engine for now we put everything


    public GearNumber getGearNumber(){
        return gearNumber;
    }

    public int getSpeed(){
        return speed;
    }

    public int getMaxSpeed(){
        return MAX_SPEED;
    }

    public int getMaxNumberGears(){
        return MAX_NUMBER_GEARS;
    }

    @Override
    public void startEngine() {
        accelerateTimer = new Timer();
        decelerateTimer = new Timer();

        accelerateTimer.schedule( new AccelerateTimerTask(), throttleSpeedIncrementalTimeInterval);
        decelerateTimer.schedule( new DecelerateTimerTask(), throttleSpeedDecrementalTimeInterval);
    }

    @Override
    public void stopEngine() {
        if(accelerateTimer != null){
            accelerateTimer.cancel();
        }
        if(decelerateTimer != null){
            decelerateTimer.cancel();
        }
    }

    @Override
    public void accelerate() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void reverse() {

    }

    @Override
    public void shiftGearUp() {

    }

    @Override
    public void shiftGearDown() {

    }

    @Override
    public void shiftToNeutral() {

    }

    private class AccelerateTimerTask extends TimerTask {

        @Override
        public void run() {

        }
    }

    private class DecelerateTimerTask extends TimerTask {

        @Override
        public void run() {

        }
    }
}
