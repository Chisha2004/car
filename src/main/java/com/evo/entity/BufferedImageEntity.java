package com.evo.entity;

import com.evo.config.GameSetting;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class BufferedImageEntity extends DrawableEntity{

    protected BufferedImage bufferedImage;
    protected String fileName;
    protected int heightFactor = 1;
    protected int widthFactor = 1;

    private Log log = LogFactory.getLog(BufferedImageEntity.class);
    @Resource
    GameSetting gameSetting;

    public BufferedImageEntity() {}
    public BufferedImageEntity(String fileName) {

        this.fileName = fileName;

        try{
            bufferedImage = ImageIO.read( new ClassPathResource(fileName).getURL());
        }catch (IOException e){
            log.error(e);
        }

    }
    @Override
    public void draw(Graphics2D graphics2D){
//        log.info(String.format("Drawing entity [%s] at: xPos: [%s]",getUniqueMapIdentifier() ,xPos));
        if(bufferedImage != null){
            graphics2D.drawImage(bufferedImage, getCustomXDrawPosOrDefault(), yPos,
                    gameSetting.getTileSize() * getWidthFactor(), gameSetting.getTileSize() * getHeightFactor(), null);
        }
    }

    //Should be overridden in subclasses
    protected int getCustomXDrawPosOrDefault(){
        return xPos;
    }

    public void setGameSetting(GameSetting gameSetting){
        this.gameSetting = gameSetting;
    }

    public void setDefaultValues(){
        //set starting pos

    }

    public void update() {
        //must be overridden if needed
    }

    public abstract int getWidthFactor();
    public abstract int getHeightFactor();

}
