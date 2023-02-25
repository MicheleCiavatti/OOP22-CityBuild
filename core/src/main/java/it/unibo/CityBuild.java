package it.unibo;

import com.badlogic.gdx.Game;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.model.api.City;
import it.unibo.model.impl.CityImpl;
import it.unibo.view.MainMenu;

public class CityBuild extends Game {
    
    public static final int GAME_WIDTH = 1440;
	public static final int GAME_HEIGHT = 810;
    
    private final Controller controller;
    private final City city;

    public CityBuild() {
        this.controller = new ControllerImpl();
        this.city = new CityImpl();
    }

    @Override
    public void create() {
        this.setScreen(new MainMenu(this));
    }
}
