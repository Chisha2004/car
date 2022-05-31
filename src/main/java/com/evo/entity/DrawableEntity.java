package com.evo.entity;

import java.awt.*;

public abstract class DrawableEntity {
    protected int xPos = 0;
    protected int yPos = 0;
    protected int speed = 0;
    public static String UNIQUE_MAP_IDENTIFIER_KEY="EMPTY"; //

    public abstract void draw(Graphics2D graphics2D);
    public abstract String getUniqueMapIdentifier();


    public void setLocation(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public int getCurrentXPos() {
        return xPos;
    }

    public int getCurrentYPos() {
        return yPos;
    }
}
