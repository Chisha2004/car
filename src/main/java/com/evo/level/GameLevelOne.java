package com.evo.level;

import com.evo.config.AppSetting;
import com.evo.config.GameLevelNumber;
import com.evo.modal.SeamlessBackgroundPanel;
import com.evo.modal.Vehicle;
import com.evo.util.BackgroundSpeedCalculator;


import javax.annotation.Resource;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class GameLevelOne extends GameLevel {

    private SeamlessBackgroundPanel backgroundPanel;
    VehicleInfoPanel vehicleInfoPanel;
    private static int GENERIC_TIMER_INTERVAL = 2; //MILLI

    @Resource
    private Vehicle vehicle;

    @Resource
    private AppSetting appSetting;

    public GameLevelOne(){
        gameLevelNumber = GameLevelNumber.ONE;
        backgroundPanel = new SeamlessBackgroundPanel(1, "/img/seamless/background-1.jpg");
    }

    public void addDefaultCharactersToScene() {
        vehicle.setUpVehicle();
        moveAllCharactersToStartPosition();
        vehicle.setFocusable(true);

        backgroundPanel.addKeyListener(vehicle);
        backgroundPanel.setFocusable(true);

        backgroundPanel.add(vehicle);
        //FIXME: For now just add a label for speed

        vehicleInfoPanel = new VehicleInfoPanel();
        backgroundPanel.add(vehicleInfoPanel);

        initGenericTimer();
    }

    protected void initGenericTimer(){
        genericTimer = new Timer();
        genericTimer.scheduleAtFixedRate( new GameLevelOne.GenericTimerTask(),0, GENERIC_TIMER_INTERVAL);
    }

    private void updateCurrentSpeedOnScreen(){
        vehicleInfoPanel.setSpeedAndGearText(String.format("Current Speed : [%s kms]",vehicle.getCurrentSpeed()),
                String.format("Current Gear : [%s]",vehicle.getCurrentGearNumber()),
                String.format("Engine Status : [%s]",vehicle.isEngineRunning()? "on" : "off"));
    }

    private void updateBackgroundPosition(){
        if(vehicle.getCurrentSpeed() > 0){
            backgroundPanel.startAnimationIfNotStarted();
            backgroundPanel.updateTimerDelay(
                    BackgroundSpeedCalculator.calculateSpeedIntervalMillis(vehicle.getCurrentSpeed()));
        }else{
            backgroundPanel.stop();
        }
    }

    @Override
    public GameLevelNumber getGameLevelNumber() {
        return gameLevelNumber;
    }

    public void moveAllCharactersToStartPosition(){

        vehicle.setLocation(0, getTruckStartingYPosition());

    }

    private int getTruckStartingYPosition(){
        int yLocation = appSetting.getAppHeight() - appSetting.getAppGroundHeight();

        return yLocation;
    }

    @Override
    public Component render() {
//        personPanel.startAnimation();
        return backgroundPanel;
    }

    protected class GenericTimerTask extends TimerTask {

        @Override
        public void run() {
            updateCurrentSpeedOnScreen();
            updateBackgroundPosition();
        }
    }
}
