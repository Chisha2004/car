package com.evo.entity;

import com.evo.config.GameSetting;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import java.awt.*;
import java.io.File;

public class SimpleCloud extends BufferedImageEntity {
    public static String UNIQUE_MAP_IDENTIFIER_KEY = "CLOUD";

    private static String GROUND_IMG_DIR = "img" + File.separator + "nature";
    private static String fileName = GROUND_IMG_DIR + File.separator + "cloud-48x17.png";
    private Log log = LogFactory.getLog(Vehicle.class);

    public SimpleCloud(GameSetting gameSetting) {
        super(fileName);
        setGameSetting(gameSetting);
    }

    @Override
    public int getWidthFactor() {
        return 3;
    }

    @Override
    public int getHeightFactor() {
        return 1;
    }


//    @Override
//    public void draw(Graphics2D graphics2D){
//        log.info(String.format("Drawing entity [%s] at: xPos: ",getUniqueMapIdentifier() ,xPos));
//
//        int offSet = 0;
//
//        if(bufferedImage != null){
//            graphics2D.drawImage(bufferedImage, xPos - offSet, yPos,
//                    gameSetting.getTileSize() * getWidthFactor(), gameSetting.getTileSize() * getHeightFactor(), null);
//        }
//
//    }

    @Override
    public String getUniqueMapIdentifier(){
        return UNIQUE_MAP_IDENTIFIER_KEY;
    }

}
