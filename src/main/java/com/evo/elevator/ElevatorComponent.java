package com.evo.elevator;

import com.evo.modal.AnimatedPanel;
import com.evo.modal.ImagePanel;
import com.evo.modal.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ElevatorComponent extends JComponent {


    public ElevatorComponent(){
        JButton quitButton = new JButton("Quit in side");

        quitButton.addActionListener((
                ActionEvent event) -> {
            System.exit(0);
        });


        ImagePanel backgroundPanel = new ImagePanel(1, "background.jpeg");

        AnimatedPanel personPanel = new Person(2);

        GroupLayout gl = new GroupLayout(this);
        setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(backgroundPanel)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(backgroundPanel)
        );
    }
}
