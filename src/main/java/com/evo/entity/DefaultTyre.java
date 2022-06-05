package com.evo.entity;

import com.evo.config.GameSetting;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultTyre extends BufferedImageEntity {
    public static String UNIQUE_MAP_IDENTIFIER_KEY = "DEFAULT_TYRE";

    private static String TYRE_IMG_DIR = "img" + File.separator + "tyre" + File.separator + "default";
//    private static String fileName = TYRE_IMG_DIR + File.separator + "tye.png";
    private int numberTyrePos = 8;
    private int currentTyreIndex = 0;
    private Log log = LogFactory.getLog(Vehicle.class);
    private List<BufferedImage> tyreCycleImages = new ArrayList<>();

    public DefaultTyre(GameSetting gameSetting) {

        setGameSetting(gameSetting);

        initImages();
    }

    public void initImages(){
        try{
            for(int i=0; i < numberTyrePos; i++) {
                tyreCycleImages.add(ImageIO.read(new ClassPathResource(String.format("%s%styre-%s.png",
                        TYRE_IMG_DIR, File.separator, i)).getURL()));
            }
        }catch (IOException e){
            log.error(e);
        }
    }

    @Override
    public int getWidthFactor() {
        return 1;
    }

    @Override
    public int getHeightFactor() {
        return 1;
    }

    public void update(){
        currentTyreIndex++;

        if(currentTyreIndex >= numberTyrePos){
            currentTyreIndex = 0;
        }
    }


    @Override
    public void draw(Graphics2D graphics2D){

        if(!tyreCycleImages.isEmpty()){
            graphics2D.drawImage(tyreCycleImages.get(currentTyreIndex), xPos, yPos,
                    gameSetting.getTileSize() * getWidthFactor(), gameSetting.getTileSize() * getHeightFactor(), null);
        }

    }

    @Override
    public String getUniqueMapIdentifier(){
        return UNIQUE_MAP_IDENTIFIER_KEY;
    }
}
