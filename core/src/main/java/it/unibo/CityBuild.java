package it.unibo;

import com.badlogic.gdx.Game;

import it.unibo.view.MainMenu;

public class CityBuild extends Game {
    
    public static final int GAME_WIDTH = 1440;
	public static final int GAME_HEIGHT = 810;

    @Override
    public void create() {
        this.setScreen(new MainMenu(this));
    }
}
