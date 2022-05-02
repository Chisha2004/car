package com.evo.elevator;

import com.evo.scene.GameLevelOne;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TruckerMainComponent extends JComponent {


    public TruckerMainComponent(){
        JButton quitButton = new JButton("Quit in side");

        quitButton.addActionListener((
                ActionEvent event) -> {
            System.exit(0);
        });


//        ImagePanel backgroundPanel = new ImagePanel(1, "background.jpeg");
//
//        AnimatedPanel personPanel = new Person(2);

        //FIXME: once we have other levels we may need to allow user to save level for now we start from level one
        GameLevelOne gameLevelOne = new GameLevelOne();

        GroupLayout gl = new GroupLayout(this);
        setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(gameLevelOne.render())
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(gameLevelOne.render())
        );
    }
}
