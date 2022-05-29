package com.evo.core;

import com.evo.config.GameLevelManager;
import com.evo.level.GameLevel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;

@Component
public class GameMainComponent extends JComponent {

    @Resource
    GameLevelManager gameLevelManager;

    public GameMainComponent(){
//        JButton quitButton = new JButton("Quit in side");
//
//        quitButton.addActionListener((
//                ActionEvent event) -> {
//            System.exit(0);
//        });


//        ImagePanel backgroundPanel = new ImagePanel(1, "background.jpeg");
//
//        AnimatedPanel personPanel = new Person(2);

        //FIXME: once we have other levels we may need to allow user to save level for now we start from level one

    }

    public void renderComponent(){
        GameLevel gameLevel = gameLevelManager.getStartLevel();
        gameLevel.addDefaultCharactersToScene();

        GroupLayout gl = new GroupLayout(this);
        setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(gameLevel.render())
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(gameLevel.render())
        );
    }
}
