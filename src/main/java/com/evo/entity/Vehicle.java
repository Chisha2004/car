package com.evo.entity;

import com.evo.engine.GearBox;
import com.evo.engine.GearNumber;
import com.evo.engine.VehicleEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

//TODO: This should be an abstract class then extended by other vehicle types
public class Vehicle extends BufferedImageEntity implements KeyListener {

    public static String UNIQUE_MAP_IDENTIFIER_KEY = "DEFAULT_CAR";
    private static final String TRUCK_IMG_DIR = "img" + File.separator + "truck";
    private static String STILL_TRUCK_IMAGE_SRC = TRUCK_IMG_DIR + File.separator + "car-400x100.png";
    private DefaultTyre frontTyre;
    private DefaultTyre backTyre;
    private static int tyreHeight = 10;
    private Log log = LogFactory.getLog(Vehicle.class);

    @Resource
    private VehicleEngine vehicleEngine;

    @Resource
    private GearBox gearBox;

    //For test purposes
    public void setVehicleEngine(VehicleEngine vehicleEngine){
        this.vehicleEngine = vehicleEngine;
    }

    public Vehicle() {
        super(STILL_TRUCK_IMAGE_SRC);
        //FIXME: for now using still image just to get default positioning
    }

    public void setUpVehicle(){
        vehicleEngine.setGearBox(gearBox);
        //FIXME: find a way to cal calculate these based on vehicle size. We only set this location once
        frontTyre = new DefaultTyre(gameSetting);
        backTyre = new DefaultTyre(gameSetting);


    }

    private void pressAccelerator(){
       //accelerate has to keep being called continuously
        vehicleEngine.pressAccelerator();
    }

    private void releaseAccelerator(){
        //accelerate has to keep being called continuously
        vehicleEngine.releaseAccelerator();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'p'){
            vehicleEngine.startEngine();

        }else if(e.getKeyChar() == 'w'){

            vehicleEngine.shiftGearUp();

        }else if(e.getKeyChar() == 's'){

            vehicleEngine.shiftGearDown();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            pressAccelerator();
        }else if(e.getKeyChar() == 's'){

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            vehicleEngine.releaseAccelerator();
        }
    }

    public int getCurrentSpeed() {
        return vehicleEngine.getCurrentSpeed();
    }

    public GearNumber getCurrentGearNumber() {
        return vehicleEngine.getCurrentGearNumber();
    }

    public boolean isEngineRunning() {
        return vehicleEngine.isEngineRunning();
    }

    public void setLocation(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setDefaultLocation(int xPos, int groundStartPos){
        int carImageOffset = tyreHeight * -1;// multiple with minus to add room bellow for the tye which is rendered separately

        super.setLocation(xPos,
                (groundStartPos - (gameSetting.getTileSize() * getHeightFactor()) + carImageOffset)); // offset by 10
    }

    //FIXME in future we can decide for a more dynamic way of know whether to make the facter 1 or more
    @Override
    public int getWidthFactor() {
        return 7;
    }
    //FIXME in future we can decide for a more dynamic way of know whether to make the facter 1 or more
    @Override
    public int getHeightFactor() {
        return 2;
    }

    @Override
    public String getUniqueMapIdentifier(){
        return UNIQUE_MAP_IDENTIFIER_KEY;
    }

    @Override
    public void update() {
        vehicleEngine.updateSpeed();
        setLocation(getNextXPosBySpeed(), yPos);

        //FIXME: find a way to cal calculate these based on vehicle size. We only set this location once. the rotate
        // needs to go with the car speed
        frontTyre.setLocation(272, yPos + 63);
        backTyre.setLocation(68, yPos + 63);
        frontTyre.update();
        backTyre.update();

        //fix me update tyre position as well
    }

    private int getNextXPosBySpeed(){
        //FIXME: we need a better way to calculate the vehicle speed ratio
        double speedFactor = Double.valueOf(getCurrentSpeed()) / 100;
        return (int) Math.ceil(Double.valueOf(getCurrentXPos()) + speedFactor);
    }

    @Override
    protected int getCustomXDrawPosOrDefault(){

        return 10;
    }

    @Override
    public void draw(Graphics2D graphics2D){
        super.draw(graphics2D);
        frontTyre.draw(graphics2D);
        backTyre.draw(graphics2D);
    }
}
