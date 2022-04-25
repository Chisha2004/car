package com.evo.elevator;

import com.evo.modal.ImagePanel;

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
