package it.unibo.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ScreenExample extends ScreenAdapter {

    private static final String SOUND_FOLDER = "sounds" + File.separator;
    private static final Rectangle NULL_RECTANGLE = new Rectangle(0, 0, 0, 0);
    private static final float BUTTON_WIDTH = 100;
    private static final float BUTTON_HEIGHT = 100;
    private static final float BUTTON_SPACING = 10;


    private final Music theme;
    private final ShapeRenderer shapeRenderer;
    private final List<Rectangle> buildings;
    private final Dialog warning;
    private final Stage stage;
    private final Rectangle border;
    private Optional<Rectangle> selected;



    private int index = 0;
    private final List<ImageButton> buttonList = new ArrayList<>();
    private String selectedBuildingName;
    private static final String EXTENSION = ".png";
    private static final int NUMBUTTONS=3;


    public ScreenExample() {
        this.theme = Gdx.audio.newMusic(Gdx.files.internal(SOUND_FOLDER + "Chill_Day.mp3"));
        this.buildings = new ArrayList<>();
        this.shapeRenderer = new ShapeRenderer();
        this.selected = Optional.empty();
        this.warning = new Dialog("Warning", new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json")));
        this.stage = new Stage(new ScreenViewport());
        this.border = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(new GameProcessor());
    }

    /**{@inheritDoc} */
    @Override
    public void show() {
        this.startMusic();
        this.warning.hide();
        this.warning.text("You can't place a building on top of another building");
        this.stage.addActor(warning);

        
        float buttonY = (Gdx.graphics.getHeight() - BUTTON_HEIGHT * 3 - BUTTON_SPACING * 2) / 2;
        
        
        for (int i = 0; i < NUMBUTTONS; i++) {
            //aggiunge i bottoni con delle immagini chiamate immagine1, immagine2, ecc
            buttonList.add(addButton((Gdx.graphics.getWidth() - BUTTON_WIDTH) / 2, buttonY, "button"+i));
            buttonY += BUTTON_HEIGHT + BUTTON_SPACING;
        }
    }

    /**{@inheritDoc} */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawRectangle(this.selected.orElse(NULL_RECTANGLE));
        this.buildings.forEach(this::drawRectangle);
        shapeRenderer.end();
        this.stage.act(delta);
        this.stage.draw();
    }

    /**{@inheritDoc} */
    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
        this.theme.dispose();
        this.stage.dispose();
    }

    private ImageButton addButton(float x, float y, String buildingName){
        Texture iconTexture = new Texture("images" + File.separator + "badlogic.jpg");
        TextureRegion icon = new TextureRegion(iconTexture);

        ImageButton button = new ImageButton(new TextureRegionDrawable(icon));
        button.setName(buildingName);
        stage.addActor(button);
        button.setPosition(x, y);
        button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedBuildingName = buildingName+EXTENSION;
                System.out.println("Selected building: " + selectedBuildingName);
            }
        });
        return button;
    }

    private void roundButtonList(int param){
        
        index = param;
        if(index < 0){
            index = buttonList.size() - 1;
        }
        if(index > buttonList.size() - 1){
            index = 0;
        }
        selectButton(index);
    }

    private void selectButton(int index){
        for (ImageButton button : buttonList) {
            button.setBounds(100, 100,0 , 0);
        }
        buttonList.get(index).setBounds(200, 200, 200, 200);

    }

    private void startMusic() {
        this.theme.play();
        this.theme.setVolume(0.25f);
        this.theme.setOnCompletionListener(Music::play);
    }

    private void drawRectangle(final Rectangle rectangle) {
        this.shapeRenderer.setColor(this.isValidPosition(rectangle)
            ? Color.GREEN
            : Color.RED);
        this.shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private boolean isValidPosition(final Rectangle rectangle) {
        return buildings.stream().noneMatch(rect -> rect.overlaps(rectangle))
            && this.border.contains(rectangle);
    }

    private class GameProcessor extends InputAdapter {

        private static final int RECT_WIDTH = 200;
        private static final int RECT_HEIGHT = 200;

        private final Sound selection;
        private final Sound destruction;
        private final Sound construction;
        private final Sound wrong;
        private final Sound scroll;
        private final Sound upgrading;
        private boolean pressingShift;
        private boolean pressingCtrl;

        public GameProcessor() {
            this.selection = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "select_building.ogg"));
            this.destruction = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "destruction.ogg"));
            this.construction = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "construction.ogg"));
            this.wrong = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "wrong1.ogg"));
            this.scroll = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "scroll.ogg"));
            this.upgrading = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "upgrade.ogg"));
            this.pressingShift = false;
            this.pressingCtrl = false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
            return selected.isPresent()
                ? this.handlePlacement()
                : this.handleTouch(screenX, screenY);
        }

        /**{@inheritDoc} */
        @Override
        public boolean keyDown(final int keycode) {
            switch(keycode) {
                case Input.Keys.Q -> this.selectingBuilding();
                case Input.Keys.SHIFT_LEFT -> this.pressingShift = true;
                case Input.Keys.CONTROL_LEFT -> this.pressingCtrl = true;
                case Input.Keys.UP -> roundButtonList(index+1);
                case Input.Keys.DOWN -> roundButtonList(index-1);
                case Input.Keys.ESCAPE -> Gdx.app.exit(); //TODO exit game.
            }
            return false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean keyUp(final int keycode) {
            switch(keycode) {
                case Input.Keys.SHIFT_LEFT -> this.pressingShift = false;
                case Input.Keys.CONTROL_LEFT -> this.pressingCtrl = false;
            }
            return true;
        }

        /**{@inheritDoc} */
        @Override
        public boolean mouseMoved(final int screenX, final int screenY) {
        if (selected.isPresent()) {
                selected.get().setPosition(this.computeX(screenX), this.computeY(screenY));
                return true;
            }
            return false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean scrolled(final float amountX, final float amountY) {
            this.scroll.play();
            return true;
        }

        private float computeX(final float screenX) {
            return screenX - RECT_WIDTH / 2;
        }
    
        private float computeY(final float screenY) {
            return Gdx.graphics.getHeight() - screenY - RECT_HEIGHT / 2;
        }

        private boolean selectingBuilding() {
            if (selected.isEmpty()) {
                this.selection.play();
                selected = Optional.of(new Rectangle(
                    computeX(Gdx.input.getX()), 
                    computeY(Gdx.input.getY()),
                    RECT_WIDTH, RECT_HEIGHT));
                    return true;
            }
            return false;
        }

        /*When the user has selected a building from the icon menù, this method 
        is used to determine the consequences of a click of the mouse */
        private boolean handlePlacement() {
            if (isValidPosition(selected.get())) {
                this.construction.play();
                buildings.add(selected.get());
            } else {
                warning.show(stage);
                warning.setPosition(Gdx.graphics.getWidth() / 2 - warning.getWidth() / 2, 
                    Gdx.graphics.getHeight() - warning.getHeight() - Gdx.graphics.getHeight() / 12);
                Timer.schedule(new Task() {
                    @Override
                    public void run() {
                        warning.hide();
                    }
                }, 2f);
                this.wrong.play();
            }
            selected = Optional.empty();
            return true;
        }

        /*This method is used to determine the consequences of a click of the mouse without carrying a building for placement. */
        private boolean handleTouch(final int screenX, final int screenY) {
            final var touched = buildings.stream()
                .filter(rect -> rect.contains(screenX, Gdx.graphics.getHeight() - screenY))
                .findFirst();
            if (touched.isPresent()) {
                if (this.pressingShift) {
                    this.destruction.play();
                    buildings.remove(touched.get());
                } else if (this.pressingCtrl) {
                    this.upgrading.play();
                }
                return true;
            }
            return false;
        }
    }
}
