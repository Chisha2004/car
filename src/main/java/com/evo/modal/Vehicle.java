package com.evo.modal;

import com.evo.engine.GearBox;
import com.evo.engine.VehicleEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

//TODO: This should be an abstract class then extended by other vehicle types
public class Vehicle extends AnimatedPanel implements KeyListener {

    private static final String TRUCK_IMG_DIR = "img" + File.separator + "truck";
    private static String STILL_TRUCK_IMAGE_SRC = TRUCK_IMG_DIR + File.separator + "car-test-200x113.png";
    private Log log = LogFactory.getLog(Vehicle.class);

    @Resource
    private VehicleEngine vehicleEngine;

    @Resource
    private GearBox gearBox;

    //For test purposes
    public void setVehicleEngine(VehicleEngine vehicleEngine){
        this.vehicleEngine = vehicleEngine;
    }

    public Vehicle(int id) {
        //FIXME: for now using still image just to get default positioning
        super(id, new String[] {STILL_TRUCK_IMAGE_SRC});
        setPaintDefaultImageIcon(true);
    }

    public void setUpVehicle(){
        vehicleEngine.setGearBox(gearBox);
    }

    private void accelerate(){
       //accelerate has to keep being called continuously
        vehicleEngine.accelerate();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'p'){
            vehicleEngine.startEngine();

        }else if(e.getKeyChar() == 'w'){

            vehicleEngine.shiftGearUp();
            log.info(String.format("Gear shifted up: Gear number: [%s]", vehicleEngine.getCurrentGearNumber()));

        }else if(e.getKeyChar() == 's'){

            vehicleEngine.shiftGearDown();
            log.info(String.format("Gear shifted down: Gear number: [%s]", vehicleEngine.getCurrentGearNumber()));

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            accelerate();
            log.info(String.format("Accelerating, Current speed: [%s]", vehicleEngine.getCurrentSpeed()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //TODO: need to decelerate slowly if the accelerate key is lifted
        // deceleration is happening by itself automatically
    }

    public int getCurrentSpeed() {
        return vehicleEngine.getCurrentSpeed();
    }
}
