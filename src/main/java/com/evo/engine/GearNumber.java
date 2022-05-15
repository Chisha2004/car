package com.evo.engine;

public enum GearNumber {
    NEUTRAL(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);
    //Add more gears as needed

    int value;

    GearNumber(int value){
        this.value = value;
    }

}
