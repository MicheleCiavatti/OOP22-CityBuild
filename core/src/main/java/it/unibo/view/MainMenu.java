package it.unibo.view;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

import it.unibo.CityBuild;

/**This class is responsible for the main menù of the game. */
public class MainMenu implements Screen {
	
	public static final int MENU_WIDTH = 1440;
	public static final int MENU_HEIGHT = 810;
	
	private static final String SOUND_FOLDER = "sounds" + File.separator;
	private static final String IMAGE_FOLDER = "images" + File.separator;
	private static final int SCREEN_DIVISOR = 12;
	private static final float BUTTON_WIDTH = 300f;
	private static final float BUTTON_HEIGHT = 150f;

	private final CityBuild game;

	private final Skin skin;
	private final Stage stage;
	private final Sound buttonClick;
	private final Music theme;

	
	public MainMenu(final CityBuild game) {
		this.game = game;
		this.buttonClick = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "button.wav"));
		this.theme = Gdx.audio.newMusic(Gdx.files.internal(SOUND_FOLDER + "tlou_theme.mp3"));
		this.stage = new Stage(new ScreenViewport());
		this.skin = new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json"));
	}

	/**{@inheritDoc} */
	@Override
	public void dispose () {
		this.buttonClick.dispose();
		this.skin.dispose();
		this.stage.dispose();
	}

	@Override
	public void show() {	
		this.theme.play();
		this.theme.setOnCompletionListener(music -> music.play());
		final int rowHeight = Gdx.graphics.getHeight() / SCREEN_DIVISOR;
		final Image background = new Image(new Texture(IMAGE_FOLDER + "CityBuild.png"));
		background.setName("Background");
		/*Centers the background. */
		background.setPosition(
			Gdx.graphics.getWidth() / 2 - background.getWidth() / 2, 
			Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);
		this.stage.addActor(background);
		final Button newGame = new TextButton("New Game", skin);
		final Button loadGame = new TextButton("Load Game", skin);
		loadGame.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		newGame.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		/*loadGame in lower part of the screen. */
		loadGame.setPosition(Gdx.graphics.getWidth() / 2 - BUTTON_WIDTH / 2, rowHeight);
		/*newGame in upper part of the screen. */
		newGame.setPosition(Gdx.graphics.getWidth() / 2 - BUTTON_WIDTH / 2, Gdx.graphics.getHeight() - BUTTON_HEIGHT - rowHeight);
		
		/*Listener for the buttons. TODO. */
		loadGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Button pressed");
				MainMenu.this.buttonClick.play();
				return true;
			} 
		});
		
		newGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Button pressed");
				MainMenu.this.buttonClick.play();
				return true;
			} 
		});
		this.stage.addActor(newGame);
		this.stage.addActor(loadGame);
		Gdx.input.setInputProcessor(this.stage);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		this.stage.act(delta);
		this.stage.draw();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
