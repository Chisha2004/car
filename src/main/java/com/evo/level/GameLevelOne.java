package com.evo.level;

import com.evo.config.GameSetting;
import com.evo.config.GameLevelNumber;
import com.evo.entity.EntityManager;
import com.evo.entity.SimpleFlatGround;
import com.evo.modal.GamePanel;
import com.evo.entity.Vehicle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import javax.annotation.Resource;
import java.awt.*;
import java.io.File;


public class GameLevelOne extends GameLevel implements Runnable {

    VehicleInfoPanel vehicleInfoPanel;
    private static int WORLD_MAP_SIZE_X = 100; //Must correspond to map txt file
    private static int WORLD_MAP_SIZE_Y = 16; //Must correspond to map txt file
    private static String MAP_DIR = "map";

    private Log log = LogFactory.getLog(GameLevelOne.class);

    @Resource
    private Vehicle selectedVehicle;

    @Resource
    private SimpleFlatGround simpleFlatGround;

    @Resource
    private GameSetting gameSetting;

    @Resource
    private EntityManager entityManager;

    public GameLevelOne(){
        gameLevelNumber = GameLevelNumber.ONE;
        worldMapFileName = MAP_DIR + File.separator + "world-map-level-1_100x16.txt";
    }

    @Override
    public void init() {

        setPreferredSize(gameSetting.getDimension());
        setBackground(Color.decode(defaultBackgroundHex));
        setDoubleBuffered(true);
        setGameSetting(gameSetting);
        setPixelMapSize(WORLD_MAP_SIZE_Y, WORLD_MAP_SIZE_X);
        setEntityManager(entityManager);
        readAndGenerateWorldMap();
        vehicle = selectedVehicle;

        vehicle.setUpVehicle();

        addKeyListener(vehicle);
        setFocusable(true);

        vehicle.setDefaultLocation(0, simpleFlatGround.getPreferredYPos());

        //FIXME: For now just add a label for speed

//        vehicleInfoPanel = new VehicleInfoPanel();
//        gamePanel.add(vehicleInfoPanel);

        initGameThread();
    }

    @Override
    protected void initGameThread(){
        gameThread = new Thread(this);
        setGameActive(true);
        gameThread.start();

    }

    private void updateCurrentSpeedOnScreen(){
        vehicleInfoPanel.setSpeedAndGearText(String.format("Current Speed : [%s kms]",vehicle.getCurrentSpeed()),
                String.format("Current Gear : [%s]",vehicle.getCurrentGearNumber()),
                String.format("Engine Status : [%s]",vehicle.isEngineRunning()? "on" : "off"));
    }

    private void updateBackgroundPosition(){
        if(vehicle.getCurrentSpeed() > 0){
//            backgroundPanel.startAnimationIfNotStarted();
//            backgroundPanel.updateTimerDelay(
//                    BackgroundSpeedCalculator.calculateSpeedIntervalMillis(vehicle.getCurrentSpeed()));
        }else{
//            backgroundPanel.stop();
        }
    }

    @Override
    public GameLevelNumber getGameLevelNumber() {
        return gameLevelNumber;
    }

    public void moveAllCharactersToStartPosition(){

        vehicle.setLocation(0, 0);

    }

    private void updateContent(){
        vehicle.update();
    }

    @Override
    public Component render() {
//        personPanel.startAnimation();
        return this;
    }

    @Override
    public void run() {

        long nanoToSeconds = 1000000000; //1000000000 makes 1 second
        double drawIntervalPerSecond = nanoToSeconds/gameSetting.getFPS(); //0.01666 seconds
        double nextNanoDrawTime = System.nanoTime() + drawIntervalPerSecond;

        while (isGameActive()){
            updateContent();

            repaint();

            try {

                double remainingNanoTimeToNextDraw = nextNanoDrawTime - System.nanoTime();

                double remainingMilliTimeToNextDraw = remainingNanoTimeToNextDraw/1000000;

                if(remainingMilliTimeToNextDraw < 0){
                    remainingMilliTimeToNextDraw = 0;
                }

                Thread.sleep((long) remainingMilliTimeToNextDraw);

                nextNanoDrawTime += drawIntervalPerSecond;

            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

}
