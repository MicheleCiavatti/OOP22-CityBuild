package it.unibo.view;

import java.io.File;
import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenExample extends ScreenAdapter implements InputProcessor {

    private static final String SOUND_FOLDER = "sounds" + File.separator;
    private static final int RECT_WIDTH = 150;
    private static final int RECT_HEIGHT = 225;
    private static final Rectangle NULL_RECTANGLE = new Rectangle(0, 0, 0, 0);

    private final Sound selection;
    private final Sound destruction;
    private final Sound construction;
    private final Sound wrong;
    private final Sound scroll;
    private final Music theme;
    private final ShapeRenderer shapeRenderer;
    private final List<Rectangle> rectangles;
    private boolean pressingShift;
    private Optional<Rectangle> rectangle;

    public ScreenExample() {
        this.selection = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "select_building.ogg"));
        this.destruction = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "destruction2.ogg"));
        this.construction = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "construction.ogg"));
        this.wrong = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "wrong1.ogg"));
        this.scroll = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER + "scroll.ogg"));
        this.theme = Gdx.audio.newMusic(Gdx.files.internal(SOUND_FOLDER + "chill_gaming_lofi.mp3"));
        this.rectangles = new ArrayList<>();
        this.shapeRenderer = new ShapeRenderer();
        this.pressingShift = false;
        this.rectangle = Optional.empty();
        Gdx.input.setInputProcessor(this);
    }

    /**{@inheritDoc} */
    @Override
    public void show() {
        this.startMusic();
    }

    /**{@inheritDoc} */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawRectangle(this.rectangle.orElse(NULL_RECTANGLE));
        this.rectangles.forEach(this::drawRectangle);
        shapeRenderer.end();
    }

    /**{@inheritDoc} */
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    /**{@inheritDoc} */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (this.rectangle.isPresent()) {
            if (this.rectangles.stream().allMatch(rect -> !rect.overlaps(this.rectangle.get()))) {
                this.construction.play();
                this.rectangles.add(this.rectangle.get());
            } else {
                this.wrong.play();
            }
            this.rectangle = Optional.empty();
        } else if (this.pressingShift) {
            var demolishing = this.rectangles.stream().filter(rect -> rect.contains(screenX, Gdx.graphics.getHeight() - screenY)).findFirst();
            if (demolishing.isPresent()) {
                this.destruction.play();
                this.rectangles.remove(demolishing.get());
            }
        }
        return true;
    }

    /**{@inheritDoc} */
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.Q -> this.selectingBuilding();
            case Input.Keys.SHIFT_LEFT -> this.pressingShift = true;
            case Input.Keys.UP -> this.scroll.play();
            case Input.Keys.DOWN -> this.scroll.play();
        }
        return false;
    }

    /**{@inheritDoc} */
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SHIFT_LEFT) {
            this.pressingShift = false;
            return true;
        }
        return false;
    }

    /**{@inheritDoc} */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
       if (this.rectangle.isPresent()) {
            this.rectangle.get().setPosition(computeX(screenX), computeY(screenY));
            return true;
       }
       return false;
    }

    private boolean selectingBuilding() {
        if (this.rectangle.isEmpty()) {
            this.selection.play();
            this.rectangle = Optional.of(new Rectangle(
                computeX(Gdx.input.getX()), 
                computeY(Gdx.input.getY()),
                RECT_WIDTH, RECT_HEIGHT));
                return true;
        }
        return false;
    }

    private void startMusic() {
        this.theme.play();
        this.theme.setVolume(0.25f);
        this.theme.setOnCompletionListener(Music::play);
    }

    private void drawRectangle(Rectangle rectangle) {
        shapeRenderer.setColor(rectangles.stream().anyMatch(rect -> rect.overlaps(rectangle))
            ? Color.RED
            : Color.GREEN);
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private float computeX(final float screenX) {
        return screenX - RECT_WIDTH / 2;
    }

    private float computeY(final float screenY) {
        return Gdx.graphics.getHeight() - screenY - RECT_HEIGHT / 2;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        this.scroll.play();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }
}
