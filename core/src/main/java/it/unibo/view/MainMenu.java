package it.unibo.view;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import it.unibo.CityBuild;

/**This class is responsible for the main menù of the game. */
public class MainMenu implements Screen {
	
	public static final int MENU_WIDTH = 1440;
	public static final int MENU_HEIGHT = 810;
	
	private static final long DELAY_CLICK_BUTTON = 25;
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
		this.theme.stop();
		this.theme.dispose();
		this.buttonClick.dispose();
		this.skin.dispose();
		this.stage.dispose();
	}

	/**{@inheritDoc} */
	@Override
	public void show() {	
		this.startMusic();
		
		final int rowHeight = Gdx.graphics.getHeight() / SCREEN_DIVISOR;
		var newGame = this.createButton("New Game", Gdx.graphics.getWidth() / 2 - BUTTON_WIDTH / 2,
			Gdx.graphics.getHeight() - BUTTON_HEIGHT - rowHeight,
			new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					buttonClick.play();
					sleeping(DELAY_CLICK_BUTTON);
					//game.setScreen(new GameScreen());
					dispose();
				}
			});
		var loadGame = this.createButton("Load Game", Gdx.graphics.getWidth() / 2 - BUTTON_WIDTH / 2, rowHeight, 
			new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					buttonClick.play();
					sleeping(DELAY_CLICK_BUTTON);
					// TODO Auto-generated method stub
					dispose();
				}
			});
		this.setStage(newGame, loadGame);
	}

	/**{@inheritDoc} */
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		this.stage.act(delta);
		this.stage.draw();
	}

	private void sleeping(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Button createButton(final String text, final float x, final float y, final ChangeListener listener) {
		Button button = new TextButton(text, this.skin);
		button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		button.setPosition(x, y);
		button.addListener(listener);
		return button;
	}

	private void setStage(final Button newGame, final Button loadGame) {
		this.stage.addActor(this.setBackground());
		this.stage.addActor(newGame);
		this.stage.addActor(loadGame);
		this.stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Input.Keys.ESCAPE) {
					Gdx.app.exit();
				}
				return true;
			}
		});
		Gdx.input.setInputProcessor(this.stage);
	}

	private void startMusic() {
		this.theme.play();
		this.theme.setOnCompletionListener(music -> music.play());
	}

	private Image setBackground() {
		final Image background = new Image(new Texture(IMAGE_FOLDER + "CityBuild.png"));
		background.setName("Background");
		/*Centers the background. */
		background.setPosition(
			Gdx.graphics.getWidth() / 2 - background.getWidth() / 2, 
			Gdx.graphics.getHeight() / 2 - background.getHeight() / 2);
		return background;
	}

	@Override
	public void hide() {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
}
