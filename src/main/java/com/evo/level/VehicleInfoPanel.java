package com.evo.level;

import javax.swing.*;

public class VehicleInfoPanel extends JPanel {
    private JLabel label;
    private JLabel gearLabel;
    private JLabel engineStatusLabel;


    public VehicleInfoPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setLocation(0, 60);

        label = new JLabel("");
        label.setOpaque(true);
        label.setSize(200,70);

        gearLabel = new JLabel("");
        gearLabel.setOpaque(true);
        gearLabel.setSize(200, 50);

        engineStatusLabel = new JLabel("");
        engineStatusLabel.setOpaque(true);
        engineStatusLabel.setSize(200, 50);

        setSize(label.getWidth(), label.getHeight());
        add(label);
        add(gearLabel);
        add(engineStatusLabel);

    }

    public void setSpeedAndGearText(String speedText, String gearText, String engineStatusText) {
        label.setText(speedText);
        gearLabel.setText(gearText);
        engineStatusLabel.setText(engineStatusText);
        repaint();
    }
}
