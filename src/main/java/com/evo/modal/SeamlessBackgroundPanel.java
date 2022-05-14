package com.evo.modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeamlessBackgroundPanel extends ImagePanel implements ActionListener {

    private Image scaled;
    private int xPos = 0;
    private int animationDelay = 300;
    private Timer animationTimer;

    public SeamlessBackgroundPanel(int identifiers, String imageName) {
        super(identifiers, imageName);
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        if(paintDefaultImageIcon) {
            graphics.drawImage(scaled, xPos, 0, this);
            //getImageIcon().paintIcon(this, graphics, 0, 0);
        }

        startAnimationIfNotStarted();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            scaled = getImage().getScaledInstance(getImage().getWidth(this), getHeight(),
                    Image.SCALE_SMOOTH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return getImage() == null ? new Dimension(200, 200) : new Dimension(
                getImage().getWidth(this), getImage().getHeight(this));
    }

    private ImageIcon getScaledImageIcon(){
//        ImageIcon imageIcon = getImageIcon().getS
        return null;
    }

    public void moveForward(){
        xPos -= 200; //FIXME: This should be frame size and not a hard coded number

        if(xPos < -1000){ //FIXME: 1000 here must be the actual width of the window. we should not allow full screen
            xPos = 0;
        }
        repaint();
    }

    public void startAnimationIfNotStarted(){

        if(animationTimer == null){
            animationTimer = new Timer(animationDelay, this);
            animationTimer.start();
        }else if(!animationTimer.isRunning()){
            animationTimer.restart();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveForward();
    }
}
