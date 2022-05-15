package com.evo.level;

import com.evo.config.AppSetting;
import com.evo.config.GameLevelNumber;
import com.evo.modal.ImagePanel;
import com.evo.modal.Person;
import com.evo.modal.SeamlessBackgroundPanel;
import com.evo.modal.Truck;


import javax.annotation.Resource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameLevelOne extends GameLevel {

    private ImagePanel backgroundPanel;
    private Person personPanel;
    private Truck truck;

    @Resource
    private AppSetting appSetting;

    public GameLevelOne(){
        gameLevelNumber = GameLevelNumber.ONE;
        backgroundPanel = new SeamlessBackgroundPanel(1, "/img/seamless/background-1.jpg");
    }

    public void addDefaultCharactersToScene() {
        truck = new Truck(2);
//        personPanel = new Person(2);
        moveAllCharactersToStartPosition();
        truck.setFocusable(true);

        backgroundPanel.addKeyListener(truck);
        backgroundPanel.setFocusable(true);

        backgroundPanel.add(truck);
    }

    @Override
    public GameLevelNumber getGameLevelNumber() {
        return gameLevelNumber;
    }

    public void moveAllCharactersToStartPosition(){

        truck.setLocation(0, getTruckStartingYPosition());

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
}
