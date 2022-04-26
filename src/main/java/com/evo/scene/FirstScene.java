package com.evo.scene;

import com.evo.modal.AnimatedPanel;
import com.evo.modal.ImagePanel;
import com.evo.modal.Person;

import java.awt.*;

public class FirstScene implements Scene {

    private ImagePanel backgroundPanel;
    private Person personPanel;

    public FirstScene(){
        backgroundPanel = new ImagePanel(1, "background.jpeg");

        addCharactersToScene();
    }

    private void addCharactersToScene() {
        personPanel = new Person(2);
    }

    @Override
    public Component render() {
        return backgroundPanel;
    }
}
