package com.evo.util;

public class BackgroundSpeedCalculator {

    public static int MAX_WORLD_CAR_SPEED = 300;

    /**
     * This is the speed interval at which we move the background in time millis. The lower the number the faster the background moves
     * Which is indicative of a faster moving vehicle. The higher the number the slower the speed
     * */
    public static int MAX_WORLD_SPEED_TIMER_INTERVAL = 1020; //This should be the slowest possible speed interval in millis
    public static int MIN_WORLD_CAR_SPEED = 5;
    public static int WORLD_TIMER_MILLIS_DECREMENT_INTERVAL = MAX_WORLD_SPEED_TIMER_INTERVAL / (MAX_WORLD_CAR_SPEED / MIN_WORLD_CAR_SPEED);
    public static int MAX_WORLD_SPEED_OFFSET_MILLIS = 200;


    /**
     * @param vehicleSpeed The current speed of the vehicle
     * @return millis
     * */
    public static int calculateSpeedIntervalMillis(int vehicleSpeed){

        //Using the vehicle speed find the number of intervals to decrement by
        int intervalMillis = (vehicleSpeed / MIN_WORLD_CAR_SPEED) * WORLD_TIMER_MILLIS_DECREMENT_INTERVAL;

        //do actual decrement from the max interval
        intervalMillis = MAX_WORLD_SPEED_TIMER_INTERVAL - intervalMillis;

        //get offset millis
        int offsetMillis = (MIN_WORLD_CAR_SPEED / MAX_WORLD_CAR_SPEED) * MAX_WORLD_SPEED_OFFSET_MILLIS;

        // add the offset
        return intervalMillis + offsetMillis;
    }

}
