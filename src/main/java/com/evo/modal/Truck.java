package com.evo.modal;

import java.io.File;

public class Truck extends AnimatedPanel{

    private static final String TRUCK_IMG_DIR = "img" + File.separator + "truck";
    private static String STILL_TRUCK_IMAGE_SRC = TRUCK_IMG_DIR + File.separator + "car-test-200x113.png";

    public Truck(int id) {
        //FIXME: for now using still image just to get default positioning
        super(id, new String[] {STILL_TRUCK_IMAGE_SRC});
        setPaintDefaultImageIcon(true);
    }

    public void drive(){
        //FIXME implement drive should also implement drive animation
    }
}
