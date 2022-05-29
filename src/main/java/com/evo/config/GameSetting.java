package com.evo.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class GameSetting {

    //SCREEN SETTINGS
    @Value("${app.game.original.tile.size}")
    public int originalTileSize; //testing now with a car of 50x30
    @Value("${app.game.tile.scale.factor}")
    public int tileFactor; //
    @Value("${app.game.max.screen.col}")
    public int maxScreenCol; // maxScreenCol is the number tileSize, if 20 then columns will be able to display
    @Value("${app.game.max.screen.row}")
    public int maxScreenRow;
    @Value("${app.game.draw.grid.lines}")
    public boolean drawGridLines;

    @Value("${app.game.fps}")
    private int FPS;  //frames per second

    private Dimension dimension;

    public GameSetting(){
        dimension = new Dimension(getScreenWidth(), getScreenHeight());
    }

    public Dimension getDimension() {
        return dimension;
    }

    public int getTileSize(){
        return originalTileSize * tileFactor;
    }

    public int getScreenWidth() {
        return (getTileSize() * maxScreenCol);
    }


    public int getScreenHeight() {
        return getTileSize() * maxScreenRow;
    }

    public int getFPS(){
        return FPS;
    }

    public int getActualScreenXPos(int xTilePos){
        return getTileSize() * xTilePos;
    }

    public int getActualScreenYPos(int xTilePos) {
        return getTileSize() * xTilePos;
    }
}
