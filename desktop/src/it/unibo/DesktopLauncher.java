package it.unibo;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import it.unibo.view.MainMenu;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(MainMenu.MENU_WIDTH, MainMenu.MENU_HEIGHT);
		config.setForegroundFPS(60);
		config.setTitle("CityBuild");
		new Lwjgl3Application(new CityBuild(), config);
	}
}
