package it.unibo;

import com.badlogic.gdx.Game;

import it.unibo.view.MainMenu;

public class CityBuild extends Game {
    
    @Override
    public void create() {
        super.setScreen(new MainMenu(this));
    }
}
