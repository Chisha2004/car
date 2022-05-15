package com.evo.modal;

import com.evo.engine.GearBox;
import com.evo.engine.TruckEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Truck extends AnimatedPanel implements KeyListener {

    private static final String TRUCK_IMG_DIR = "img" + File.separator + "truck";
    private static String STILL_TRUCK_IMAGE_SRC = TRUCK_IMG_DIR + File.separator + "car-test-200x113.png";
    private Log log = LogFactory.getLog(Truck.class);

    @Resource
    private TruckEngine truckEngine;

    public Truck(int id) {
        //FIXME: for now using still image just to get default positioning
        super(id, new String[] {STILL_TRUCK_IMAGE_SRC});
        setPaintDefaultImageIcon(true);
    }

    private void accelerate(){
       //accelerate has to keep being called continuously

    }

    @Override
    public void keyTyped(KeyEvent e) {
        log.info(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            accelerate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
