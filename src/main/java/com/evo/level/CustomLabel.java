package com.evo.level;

import javax.swing.*;

public class CustomLabel extends JPanel {
    private JLabel label;

    public CustomLabel(String labelText) {
        setLayout(null);
        setOpaque(true);
        setLocation(0, 100);

        label = new JLabel(labelText);
        label.setOpaque(true);
        label.setSize(250,250);
        label.setLocation(0,0);
        setSize(label.getWidth(), label.getHeight());
        add(label);

    }

    public void setText(String text) {
        label.setText(text);
        repaint();
    }
}
