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
    private static int WORLD_MAP_SIZE_X = 20; //Must correspond to map txt file
    private static int WORLD_MAP_SIZE_Y = 20; //Must correspond to map txt file
    private static String MAP_DIR = "map";
    private static String WORLD_MAP_FILE_NAME = MAP_DIR + File.separator + "test-map.txt";

    private Log log = LogFactory.getLog(GameLevelOne.class);

    @Resource
    private Vehicle vehicle;

    @Resource
    private SimpleFlatGround simpleFlatGround;

    @Resource
    private GameSetting gameSetting;

    @Resource
    private EntityManager entityManager;

    public GameLevelOne(){
        gameLevelNumber = GameLevelNumber.ONE;
        gamePanel = new GamePanel();
    }

    @Override
    public void addDefaultCharactersToScene() {

        gamePanel.setPreferredSize(gameSetting.getDimension());
        gamePanel.setBackground(Color.decode(defaultBackgroundHex));
        gamePanel.setDoubleBuffered(true);
        gamePanel.setGameSetting(gameSetting);

        setWorldMapFileName(WORLD_MAP_FILE_NAME);
        setPixelMapSize(WORLD_MAP_SIZE_X, WORLD_MAP_SIZE_Y);
        readAndGenerateWorldMap(entityManager, gameSetting); //FIXME: maybe make this call accept all three params above



        vehicle.setUpVehicle();
        moveAllCharactersToStartPosition();
        //vehicle.setFocusable(true);

        gamePanel.addKeyListener(vehicle);
        gamePanel.setFocusable(true);

        vehicle.setDefaultLocation(0, simpleFlatGround.getPreferredYPos());
        gamePanel.addDrawableEntity(vehicle);


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

    private int getTruckStartingYPosition(){
//        int yLocation = gameSetting.getAppHeight() - gameSetting.getAppGroundHeight();

        return 100; //FIXME: need to use map to get correct starting pos
    }

    protected void updateContent(){

    }

    @Override
    public Component render() {
//        personPanel.startAnimation();
        return gamePanel;
    }

    @Override
    public void run() {

        double drawIntervalPerSecond = 1000000000/gameSetting.getFPS(); //0.01666 seconds
        double nextNanoDrawTime = System.nanoTime() + drawIntervalPerSecond;

        while (isGameActive()){
            updateContent();

            gamePanel.repaint();

            try {

                double remainingNanoTimeToNextDraw = nextNanoDrawTime - System.nanoTime();
                double remainingMilliTimeToNextDraw = remainingNanoTimeToNextDraw/ 100000;

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
