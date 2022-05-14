package com.evo;

import com.evo.config.AppSetting;
import com.evo.core.TruckerMainComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;
import java.awt.*;


@SpringBootApplication
@ComponentScan({"com.evo.config","com.evo.core", "com.evo.level"})
public class TruckerApp extends JFrame {

	private TruckerMainComponent mainComponent;

	private AppSetting appSetting;

	private static String APP_TITLE = "Trucker";

	@Autowired
	public TruckerApp(AppSetting appSetting, TruckerMainComponent mainComponent) {
		this.appSetting = appSetting;
		this.mainComponent = mainComponent;

		initUI();
	}

	private void initUI() {

		mainComponent.renderComponent();

		createLayout(mainComponent);

		setTitle(APP_TITLE);
		setSize(appSetting.getAppWidth(), appSetting.getAppHeight());
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

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(TruckerApp.class)
				.headless(false).run(args);

		EventQueue.invokeLater(() -> {

			TruckerApp ex = ctx.getBean(TruckerApp.class);
			ex.setVisible(true);
		});
	}

}
