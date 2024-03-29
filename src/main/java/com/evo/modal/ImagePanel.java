package com.evo.modal;

import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ImagePanel extends JPanel {

    private int ID;

    //on screen position
    private Point2D.Double position;
    //imageIcon paint on screen
    private ImageIcon imageIcon;

    protected boolean paintDefaultImageIcon = true;

    private Set panelChildren;

    public ImagePanel(int identifiers, String imageName){

        super(null); // no layout
        setOpaque(false); //make transparent

        ID = identifiers;
        position = new Point2D.Double(0, 0);
        setLocation(0, 0);

        try{
            imageIcon = getImageIconFromFile(imageName);

        }catch (Exception e){

        }

        Image image = imageIcon.getImage();
        setSize(image.getWidth(this), image.getHeight(this));

        panelChildren = new HashSet();
    }

    protected ImageIcon getImageIconFromFile(String imageName) throws IOException {
        return new ImageIcon( new ClassPathResource(imageName).getURL());
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        if(paintDefaultImageIcon) {
            imageIcon.paintIcon(this, graphics, 0, 0);
        }
    }

    public void add(ImagePanel panel){
        panelChildren.add(panel);
        super.add(panel);
    }

    public void add(ImagePanel panel, int index){
        panelChildren.add(panel);
        super.add(panel, index);
    }

    public void remove(ImagePanel panel){
        panelChildren.remove(panel);
        super.remove(panel);
    }

    public void setIcon(ImageIcon icon){
        imageIcon = icon;
    }

    public void setPosition(double x, double y){
        position.setLocation(x, y);
        setLocation((int) x, (int) y);
    }

    public int getId(){
        return ID;
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public Image getImage(){
        return imageIcon.getImage();
    }

    public Set getPanelChildren() {
        return panelChildren;
    }

    public void setPaintDefaultImageIcon(boolean paintDefaultImageIcon) {
        this.paintDefaultImageIcon = paintDefaultImageIcon;
    }
}
