package it.unibo;

import com.badlogic.gdx.Game;

import it.unibo.controller.BackgroundTask;
import it.unibo.controller.impl.BackgroundTaskImpl;
import it.unibo.view.MainMenu;

public class CityBuild extends Game {
    
    public static final int GAME_WIDTH = 1440;
	public static final int GAME_HEIGHT = 810;
    BackgroundTaskImpl task = new BackgroundTaskImpl();

    @Override
    public void create() {
        this.setScreen(new MainMenu(this));

        //fa partire il thread quando applicazione è chiusa 
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook is running !");
            task.run();
        }));
    }
}
