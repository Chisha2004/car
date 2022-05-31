package com.evo.modal;

import com.evo.config.GameLevelManager;
import com.evo.config.GameSetting;
import com.evo.entity.BufferedImageEntity;
import jdk.nashorn.internal.objects.annotations.Property;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class GamePanel extends JPanel {

    Queue<BufferedImageEntity> bufferedImageEntities = new LinkedList<>();
    protected GameSetting gameSetting;
    private boolean painting = false;


    @Override
    public void paintComponent(Graphics g){

        painting = true;

        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        //FIXME for test draw pixel boxes to see the size
        //This should only be in dev mode
        if(gameSetting.drawGridLines) {
            drawPixelGridLines(graphics2D);
        }

        paintGameContent(graphics2D);


        graphics2D.dispose();

        painting = false;
    }

    protected void paintGameContent(Graphics2D graphics2D){
        // should be overridden
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

    public void setGameSetting(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }
}
