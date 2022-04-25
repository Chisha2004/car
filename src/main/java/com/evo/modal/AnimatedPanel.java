package com.evo.modal;

import javax.swing.*;
import java.util.List;

public class AnimatedPanel extends MovingPanel{

    private boolean animating;

    // frame cycle rate (i.e., rate advancing to next frame)
    private int animationRate;

    private int animationRateCounter;
    private boolean cycleForward = true;

    //individual ImageIcons used for animation frames
    private ImageIcon imageIcons[];

    // storage for all frame sequences
    private List frameSequence;
    private int currentAnimation;

    private boolean loop;
    private boolean displayLastFrame;

    private int currentFrameCounter;


    public AnimatedPanel(int id, String imageNames[]){
        super(id, imageNames[0]);

        imageIcons = new ImageIcon[imageNames.length];

        
    }

}
