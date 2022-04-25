package com.evo.elevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SpringBootApplication
public class ElevatorApp extends JFrame {

	private ElevatorComponent mainComponent;
	private static int APP_WIDTH = 910;
	private static int APP_HEIGHT = 900;
	private static String APP_TITLE = "Elevator Madness";

	public ElevatorApp() {

		initUI();
	}

	private void initUI() {

		mainComponent = new ElevatorComponent();



		createLayout(mainComponent);

		setTitle(APP_TITLE);
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent... arg) {

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addComponent(arg[0])
		);

		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(arg[0])
		);
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ElevatorApp.class)
				.headless(false).run(args);

		EventQueue.invokeLater(() -> {

			ElevatorApp ex = ctx.getBean(ElevatorApp.class);
			ex.setVisible(true);
		});
	}

}
