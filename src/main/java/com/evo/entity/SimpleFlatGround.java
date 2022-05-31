package com.evo.entity;

import com.evo.config.GameSetting;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import java.awt.Graphics2D;
import java.io.File;

public class SimpleFlatGround extends BufferedImageEntity {

    public static String UNIQUE_MAP_IDENTIFIER_KEY = "SFG";
    private static String GROUND_IMG_DIR = "img" + File.separator + "ground";
    private static String fileName = GROUND_IMG_DIR + File.separator + "ground-50x34.png";
    private Log log = LogFactory.getLog(Vehicle.class);

    public SimpleFlatGround() {
        super(fileName);
    }

    public SimpleFlatGround(GameSetting gameSetting) {
        super(fileName);
        setGameSetting(gameSetting);
    }

    @Override
    public int getWidthFactor() {
        return 4;
    }

    @Override
    public int getHeightFactor() {
        return 4;
    }

    /**
     * @return the ground height in pixels
     * */
    public int getGroundPixelHeight() {
        //FIXME: for now we just hard cording this
//        return gameSetting.getScreenHeight() - (gameSetting.getTileSize() * getHeightFactor());

        return 4; //3 pixels
    }

    public int getPreferredYPos(){
        return gameSetting.getScreenHeight() - (gameSetting.getTileSize() * getGroundPixelHeight());
    }

//    @Override
//    public void draw(Graphics2D graphics2D){
//        log.info(String.format("Drawing entity [%s] at: xPos: ",getUniqueMapIdentifier() ,xPos));
//
//        int offSet = 0;
////        while(customXPos < gameSetting.getScreenWidth()){
//        if(bufferedImage != null){
//            graphics2D.drawImage(bufferedImage, xPos - offSet, yPos,
//                    gameSetting.getTileSize() * getWidthFactor(),
//                    gameSetting.getTileSize() * getHeightFactor(), null);
//        }
//
////            customXPos += (gameSetting.getTileSize() * getWidthFactor()) - offSet; //image offset by 10
////        }
//
//
//    }

    @Override
    public String getUniqueMapIdentifier(){
        return UNIQUE_MAP_IDENTIFIER_KEY;
    }
}
