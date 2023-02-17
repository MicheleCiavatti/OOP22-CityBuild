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
	
	public static final int MENU_WIDTH = 1440;
	public static final int MENU_HEIGHT = 810;
	
	private static final int SCREEN_DIVISOR = 12;
	private static final float BUTTON_WIDTH = 300f;
	private static final float BUTTON_HEIGHT = 150f;

	private Stage stage;
	
	@Override
	public void create () {
		final int rowHeight = Gdx.graphics.getHeight() / SCREEN_DIVISOR;
		this.stage = new Stage(new ScreenViewport());
		final Image background = new Image(new Texture("CityBuild.png"));
		background.setName("Background");
		background.setPosition(
			Gdx.graphics.getWidth() / 2 - background.getWidth() / 2, 
			Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);
		this.stage.addActor(background);
		final Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
		final Button newGame = new TextButton("New Game", skin);
		final Button loadGame = new TextButton("Load Game", skin);
		loadGame.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		newGame.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		/*loadGame in lower part of the screen. */
		loadGame.setPosition(Gdx.graphics.getWidth() / 2 - BUTTON_WIDTH / 2, rowHeight);
		/*newGame in upper part of the screen. */
		newGame.setPosition(Gdx.graphics.getWidth() / 2 - BUTTON_WIDTH / 2, Gdx.graphics.getHeight() - BUTTON_HEIGHT - rowHeight);
		loadGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Button pressed");
				return true;
			} 
		});
		newGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Button pressed");
				return true;
			} 
		});
		this.stage.addActor(newGame);
		this.stage.addActor(loadGame);
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
