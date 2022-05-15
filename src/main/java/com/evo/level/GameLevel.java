package com.evo.level;

import com.evo.config.GameLevelNumber;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public abstract class GameLevel{
    protected GameLevelNumber gameLevelNumber = null;

    public abstract Component render();
    public abstract void addDefaultCharactersToScene();
    public abstract GameLevelNumber getGameLevelNumber();
    public abstract void moveAllCharactersToStartPosition();
}
