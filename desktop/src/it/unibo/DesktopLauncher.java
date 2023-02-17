package it.unibo;

import java.awt.Toolkit;
import java.awt.Dimension;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import it.unibo.CityBuild;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		var size = Toolkit.getDefaultToolkit().getScreenSize();
		config.setWindowedMode((int) size.getWidth(), (int) size.getHeight());
		config.setForegroundFPS(60);
		config.setTitle("CityBuild");
		new Lwjgl3Application(new CityBuild(), config);
	}
}
