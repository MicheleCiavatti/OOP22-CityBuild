package it.unibo;

import com.badlogic.gdx.Game;

public class Menu extends Game{

    @Override
    public void create() {
        this.setScreen(new BuildingsChoiceMenu());
    }
    
}
