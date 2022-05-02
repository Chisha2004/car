package com.evo.modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AnimatedPanel extends MovingPanel implements ActionListener {

    private boolean animating;

    // frame cycle rate (i.e., rate advancing to next frame)
    private int animationRate;
    private int animationDelay = 300; // mil

    private int animationRateCounter;
    private boolean cycleForward = true;

    //individual ImageIcons used for animation frames
    private ImageIcon imageIcons[];

    // storage for all frame sequences
    private List frameSequence;

    private boolean loop;
    private boolean displayLastFrame;

    private int currentFrameCounter = 0;

    private Timer animationTimer;


    public AnimatedPanel(int id, String imageNames[]){
        super(id, imageNames[0]);

        setPaintDefaultImageIcon(false);

        imageIcons = new ImageIcon[imageNames.length];

        for(int i=0; i < imageIcons.length; i++){

            try{
                imageIcons[i] = getImageIconFromFile(imageNames[i]);
            }catch (Exception e){

            }
        }

    }

    public void startAnimation(){

        if(animationTimer == null){
            currentFrameCounter = 0;
            animationTimer = new Timer(animationDelay, this);
            animationTimer.start();
        }else if(!animationTimer.isRunning()){
            animationTimer.restart();
        }

    }

    public void stopAnimation(){
        if(animationTimer != null && animationTimer.isRunning()){
            animationTimer.stop();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        imageIcons[currentFrameCounter].paintIcon(this, g, 0, 0);

        if(animationTimer != null && animationTimer.isRunning()){
            currentFrameCounter = (currentFrameCounter + 1) % imageIcons.length;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
