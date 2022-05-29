package com.evo.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import java.awt.*;
import java.io.File;

@Scope("prototype")
public class SimpleCloud extends BufferedImageEntity {

    public static final String UNIQUE_MAP_IDENTIFIER_KEY="CLOUD";

    private static String GROUND_IMG_DIR = "img" + File.separator + "nature";
    private static String fileName = GROUND_IMG_DIR + File.separator + "cloud-48x17.png";
    private Log log = LogFactory.getLog(Vehicle.class);

    public SimpleCloud() {
        super(fileName);
    }

    @Override
    public int getWidthFactor() {
        return 3;
    }

    @Override
    public int getHeightFactor() {
        return 1;
    }


    @Override
    public void draw(Graphics2D graphics2D){

        int offSet = 0;

        if(bufferedImage != null){
            graphics2D.drawImage(bufferedImage, xPos - offSet, yPos,
                    gameSetting.getTileSize() * getWidthFactor(), gameSetting.getTileSize() * getHeightFactor(), null);
        }

    }

    @Override
    public String getUniqueMapIdentifier(){
        return UNIQUE_MAP_IDENTIFIER_KEY;
    }

}
