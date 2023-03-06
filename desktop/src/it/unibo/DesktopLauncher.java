package it.unibo;

import java.io.File;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	
	private static final String ICON_PATH = "images" + File.separator + "icon.jpg";
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setResizable(false);
		config.setWindowedMode(CityBuild.GAME_WIDTH, CityBuild.GAME_HEIGHT);
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setTitle("CityBuild");
		config.setWindowIcon(ICON_PATH);
		new Lwjgl3Application(new CityBuild(), config);
	}
}
