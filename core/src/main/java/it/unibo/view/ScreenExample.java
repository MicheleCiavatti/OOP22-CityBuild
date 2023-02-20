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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ScreenExample extends ScreenAdapter {

    private static final String SOUND_FOLDER = "sounds" + File.separator;
    private static final Rectangle NULL_RECTANGLE = new Rectangle(0, 0, 0, 0);

    private final Music theme;
    private final ShapeRenderer shapeRenderer;
    private final List<Rectangle> buildings;
    private final Dialog warning;
    private final Stage stage;
    private Optional<Rectangle> selected;

    public ScreenExample() {
        this.theme = Gdx.audio.newMusic(Gdx.files.internal(SOUND_FOLDER + "chill_gaming_lofi.mp3"));
        this.buildings = new ArrayList<>();
        this.shapeRenderer = new ShapeRenderer();
        this.selected = Optional.empty();
        this.warning = new Dialog("Warning", new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json")));
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(new GameProcessor());
    }

    /**{@inheritDoc} */
    @Override
    public void show() {
        this.startMusic();
        this.warning.hide();
        this.warning.text("You can't place a building on top of another building");
        this.stage.addActor(warning);
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
        shapeRenderer.dispose();
    }

    List<Rectangle> getBuildings() {
        return this.getBuildings();
    }

    Optional<Rectangle> getSelected() {
        return this.selected;
    }

    Dialog getWarning() {
        return this.warning;
    }

    Stage getStage() {
        return this.stage;
    }

    void setSelected(Optional<Rectangle> newSelected) {
        this.selected = newSelected;
    }

    private void startMusic() {
        this.theme.play();
        this.theme.setVolume(0.25f);
        this.theme.setOnCompletionListener(Music::play);
    }

    private void drawRectangle(final Rectangle rectangle) {
        shapeRenderer.setColor(buildings.stream().anyMatch(rect -> rect.overlaps(rectangle))
            ? Color.RED
            : Color.GREEN);
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private class GameProcessor extends InputAdapter {

        private static final int RECT_WIDTH = 150;
        private static final int RECT_HEIGHT = 225;

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
            this.destruction = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "destruction2.ogg"));
            this.construction = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "construction.ogg"));
            this.wrong = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "wrong1.ogg"));
            this.scroll = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "scroll.ogg"));
            this.upgrading = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "upgrade.ogg"));
            this.pressingShift = false;
            this.pressingCtrl = false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (selected.isPresent()) {
                if (buildings
                        .stream()
                        .allMatch(rect -> !rect.overlaps(selected.get()))) {
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
            } else {
                var touched = buildings
                        .stream()
                        .filter(rect -> rect.contains(screenX, Gdx.graphics.getHeight() - screenY))
                        .findFirst();
                if (touched.isPresent()) {
                    if (this.pressingShift) {
                        this.destruction.play();
                        buildings.remove(touched.get());
                    } else if (this.pressingCtrl) {
                        this.upgrading.play();
                    }
                }
                return true;
            }
            return false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean keyDown(int keycode) {
            switch(keycode) {
                case Input.Keys.Q -> this.selectingBuilding();
                case Input.Keys.SHIFT_LEFT -> this.pressingShift = true;
                case Input.Keys.CONTROL_LEFT -> this.pressingCtrl = true;
                case Input.Keys.UP -> this.scroll.play();
                case Input.Keys.DOWN -> this.scroll.play();
            }
            return false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean keyUp(int keycode) {
            switch(keycode) {
                case Input.Keys.SHIFT_LEFT -> this.pressingShift = false;
                case Input.Keys.CONTROL_LEFT -> this.pressingCtrl = false;
            }
            return true;
        }

        /**{@inheritDoc} */
        @Override
        public boolean mouseMoved(int screenX, int screenY) {
        if (selected.isPresent()) {
                selected.get().setPosition(computeX(screenX), computeY(screenY));
                return true;
            }
            return false;
        }

        /**{@inheritDoc} */
        @Override
        public boolean scrolled(float amountX, float amountY) {
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
    }
}
