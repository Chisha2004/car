package com.evo.entity;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class InGameInfo extends DrawableEntity{
    private int panelWith = 300;
    private int panelHeight = 90;
    private int textPadding = 20;
    private int xOffSet = panelWith / 2;

    private Vehicle vehicle;

    public InGameInfo(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundRect = new RoundRectangle2D.Float(xPos - xOffSet, yPos, panelWith, panelHeight, 20, 20);
        graphics2D.setPaint(Color.WHITE);
        graphics2D.draw(roundRect);
        graphics2D.setPaint(new Color(0,0,0, 153));
        graphics2D.fill(roundRect);

        graphics2D.setColor(Color.white);
//        graphics2D.setFont(new Font(null, Font.BOLD, 14));

        graphics2D.drawString(
                String.format("Current Speed: %s", vehicle.getCurrentSpeed()), xPos + textPadding - xOffSet, yPos + textPadding);
        graphics2D.drawString(
                String.format("Current Gear: %s", vehicle.getCurrentGearNumber()), xPos + textPadding - xOffSet, yPos + textPadding * 2);
        graphics2D.drawString(
                String.format("Engine: %s", vehicle.isEngineRunning()? "On" : "Off"), xPos + textPadding - xOffSet, yPos + textPadding * 3);

    }

    @Override
    public String getUniqueMapIdentifier() {
        return null;
    }
}
