package com.evo.entity;

import java.awt.*;

public abstract class DrawableEntity {
    protected int xPos = 0;
    protected int yPos = 0;
    protected int speed = 0;
    protected String uniqueMapIdentifier="EMPTY"; //

    public abstract void draw(Graphics2D graphics2D);
    public abstract String getUniqueMapIdentifier();

    public void setDefaultLocation(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }



}
