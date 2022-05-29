package com.evo.modal;

import com.evo.config.GameSetting;
import com.evo.entity.BufferedImageEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    List<BufferedImageEntity> bufferedImageEntities = new ArrayList<BufferedImageEntity>();
    protected GameSetting gameSetting;

    public void update(){
        for(BufferedImageEntity bufferedImage : bufferedImageEntities){
            bufferedImage.update();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        //FIXME for test draw pixel boxes to see the size
        if(gameSetting.drawGridLines) {
            drawPixelGridLines(graphics2D);
        }
        for(BufferedImageEntity bufferedImage : bufferedImageEntities){
            bufferedImage.draw(graphics2D);
        }

        graphics2D.dispose();
    }

    private void drawPixelGridLines(Graphics2D graphics2D) {

        if(gameSetting != null) {
            int drawYPoint = 0;
            int drawXPoint = 0;

            while(drawXPoint < gameSetting.getScreenWidth() && drawYPoint < gameSetting.getScreenHeight()){

                graphics2D.drawRect(drawXPoint, drawYPoint,
                        gameSetting.getTileSize(), gameSetting.getTileSize());

                drawXPoint += gameSetting.getTileSize();

                if(drawXPoint >= gameSetting.getScreenWidth() && drawYPoint <= gameSetting.getScreenHeight()){
                    drawXPoint = 0;
                    drawYPoint += gameSetting.getTileSize();
                }
            }

        }
    }

    public void addDrawableEntity(BufferedImageEntity bufferedImage) {
        bufferedImageEntities.add(bufferedImage);
    }

    public void setGameSetting(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }
}
