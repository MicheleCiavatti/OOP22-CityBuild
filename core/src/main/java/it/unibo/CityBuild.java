package it.unibo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CityBuild extends ApplicationAdapter {
	
	public static int MENU_WIDTH = 960;
	public static int MENU_HEIGHT = 540;

	private Stage stage;
	
	@Override
	public void create () {
		this.stage = new Stage(new ScreenViewport());
		Image background = new Image(new Texture("CityBuild.jpg"));
		background.setName("Background");
		background.setPosition(
			Gdx.graphics.getWidth() / 2 - background.getWidth() / 2, 
			Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);
		this.stage.addActor(background);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		this.stage.act(Gdx.graphics.getDeltaTime());
		this.stage.draw();
	}
	
	@Override
	public void dispose () {
		this.stage.dispose();
	}
}
