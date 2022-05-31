package com.evo.level;

import com.evo.config.GameLevelManager;
import com.evo.config.GameLevelNumber;
import com.evo.modal.GamePanel;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class GameLevelOneTest {

    @Test
    public void testGameLevelOneName(){
        GameLevelOne gameLevelOne = new GameLevelOne();

        Assert.notNull(gameLevelOne.getGameLevelNumber(), "Should not be null");
        Assert.isTrue(GameLevelNumber.ONE.equals(gameLevelOne.getGameLevelNumber()),
                "Should be equal to GameLevelNumber.ONE");
    }
}
