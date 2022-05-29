package com.evo;

import com.evo.config.GameSetting;
import com.evo.core.GameMainComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;
import java.awt.*;


@SpringBootApplication
@ComponentScan({"com.evo.config","com.evo.core", "com.evo.level", "com.evo.engine", "com.evo.entity"})
public class VehicleApp extends JFrame {

	private GameMainComponent mainComponent;

	private GameSetting appSetting;

	private static String APP_TITLE = "Vehicle";

	@Autowired
	public VehicleApp(GameSetting appSetting, GameMainComponent mainComponent) {
		this.appSetting = appSetting;
		this.mainComponent = mainComponent;

		initUI();
	}

	private void initUI() {

		mainComponent.renderComponent();

		createLayout(mainComponent);

		setTitle(APP_TITLE);
		setSize(appSetting.getScreenWidth(), appSetting.getScreenHeight());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
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

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(VehicleApp.class)
				.headless(false).run(args);

		EventQueue.invokeLater(() -> {

			VehicleApp ex = ctx.getBean(VehicleApp.class);
			ex.setVisible(true);
		});
	}

}
