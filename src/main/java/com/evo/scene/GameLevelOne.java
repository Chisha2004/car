package com.evo.scene;

import com.evo.modal.ImagePanel;
import com.evo.modal.Person;
import com.evo.modal.SeamlessBackgroundPanel;

import java.awt.*;

public class GameLevelOne implements GameLevel {

    private ImagePanel backgroundPanel;
    private Person personPanel;

    public GameLevelOne(){
        backgroundPanel = new SeamlessBackgroundPanel(1, "/img/seamless/background-1.jpg");

        addCharactersToScene();
    }

    private void addCharactersToScene() {
//        personPanel = new Person(2);
//        backgroundPanel.add(personPanel);
    }

    @Override
    public Component render() {
//        personPanel.startAnimation();
        return backgroundPanel;
    }
}
