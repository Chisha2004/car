package com.evo.level;

import com.evo.config.AppSetting;
import com.evo.config.GameLevelNumber;
import com.evo.engine.DefaultVehicleEngine;
import com.evo.modal.ImagePanel;
import com.evo.modal.Person;
import com.evo.modal.SeamlessBackgroundPanel;
import com.evo.modal.Vehicle;
import com.evo.util.BackgroundSpeedCalculator;


import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class GameLevelOne extends GameLevel {

    private SeamlessBackgroundPanel backgroundPanel;
    CustomLabel speedLabel;
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
//        personPanel = new Person(2);
        moveAllCharactersToStartPosition();
        vehicle.setFocusable(true);

        backgroundPanel.addKeyListener(vehicle);
        backgroundPanel.setFocusable(true);

        backgroundPanel.add(vehicle);
        JPanel testPanel = new JPanel( new BorderLayout());
        //FIXME: For now just add a label for speed

        speedLabel = new CustomLabel("Current Speed: " + 0);
        backgroundPanel.add(speedLabel);

        initGenericTimer();
    }

    protected void initGenericTimer(){
        genericTimer = new Timer();
        genericTimer.scheduleAtFixedRate( new GameLevelOne.GenericTimerTask(),0, GENERIC_TIMER_INTERVAL);
    }

    private void updateCurrentSpeedOnScreen(){
        speedLabel.setText(String.format("Current Speed : [%s kms]",vehicle.getCurrentSpeed()));
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
