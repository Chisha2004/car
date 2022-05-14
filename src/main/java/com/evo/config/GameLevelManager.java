package com.evo.config;

import com.evo.level.GameLevel;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Map;

public class GameLevelManager {

    private int currentLevelNumber = -1;
    private static final int TOTAL_NUMBER_LEVELS = 1;

    @Autowired
    private Map<String, GameLevel> gameLevels;
    private GameLevelNumber[] gameLevelNumbers;


    public GameLevelManager(){
        gameLevelNumbers = new GameLevelNumber[TOTAL_NUMBER_LEVELS];

        gameLevelNumbers[0] = GameLevelNumber.ONE;
    }


    public GameLevel getCurrentLevel(){
        return gameLevels.get(currentLevelNumber);
    }

    public GameLevel goToNextLevel(){

        //FIXME: need to know when we on the last level
        return gameLevels.get(++currentLevelNumber);
    }

    public GameLevel getStartLevel() {
        currentLevelNumber = 0;
        return gameLevels.get(gameLevelNumbers[currentLevelNumber].value);
    }
}
