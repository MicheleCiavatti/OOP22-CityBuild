package it.unibo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CityBuild extends ApplicationAdapter {
	
	public static int MENU_WIDTH = 1440;
	public static int MENU_HEIGHT = 810;

	private static float BUTTON_WIDTH = 300f;
	private static float BUTTON_HEIGHT = 150f;

	private Stage stage;
	
	@Override
	public void create () {
		this.stage = new Stage(new ScreenViewport());
		Image background = new Image(new Texture("CityBuild.png"));
		background.setName("Background");
		background.setPosition(
			Gdx.graphics.getWidth() / 2 - background.getWidth() / 2, 
			Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);
		this.stage.addActor(background);

		Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
		Button newGame = new TextButton("New Game", skin);
		newGame.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		newGame.setPosition(Gdx.graphics.getWidth() / 2 - BUTTON_WIDTH / 2, Gdx.graphics.getHeight() - BUTTON_HEIGHT);
		newGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Button pressed");
				return true;
			} 
		});
		this.stage.addActor(newGame);
		Gdx.input.setInputProcessor(this.stage);

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
