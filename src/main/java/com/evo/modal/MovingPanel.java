package com.evo.modal;

public class MovingPanel extends ImagePanel{
    private boolean moving;

    //number of pixels MovingPanel moves in both x and y values
    //per animationDelay milliseconds
    private double xVelocity;
    private double yVelocity;

    public MovingPanel(int id, String imageName){
        super(id, imageName);

        xVelocity = 0;
        yVelocity = 0;
    }

    public void animate(){
        if(isMoving()){
            double oldXPosition = getPosition().getX();
            double oldYPosition = getPosition().getY();

            setPosition(oldXPosition + xVelocity, oldYPosition + yVelocity);

        }
    }

    private boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setVelocity(double x, double y){
       xVelocity = x;
       yVelocity = y;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }
}
