package it.unibo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenExample extends ScreenAdapter implements InputProcessor {

    private static final int RECT_WIDTH = 150;
    private static final int RECT_HEIGHT = 225;
    private static final Rectangle NULL_RECTANGLE = new Rectangle(0, 0, 0, 0);

    private final ShapeRenderer shapeRenderer;
    private final List<Rectangle> rectangles;
    private Optional<Rectangle> rectangle;

    public ScreenExample() {
        this.rectangles = new ArrayList<>();
        this.shapeRenderer = new ShapeRenderer();
        rectangle = Optional.empty();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (this.rectangle.isPresent()) {
            this.rectangles.add(this.rectangle.get());
            this.rectangle = Optional.empty();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);
        drawRectangle(this.rectangle.orElse(NULL_RECTANGLE));
        this.rectangles.forEach(this::drawRectangle);
        shapeRenderer.end();
    }

    private void drawRectangle(Rectangle rectangle) {
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private float computeX(final float screenX) {
        return screenX - RECT_WIDTH / 2;
    }

    private float computeY(final float screenY) {
        return Gdx.graphics.getHeight() - screenY - RECT_HEIGHT / 2;
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if ((keycode == Input.Keys.ENTER || keycode == Input.Keys.Q) && this.rectangle.isEmpty()) {
            this.rectangle = Optional.of(new Rectangle(
                computeX(Gdx.input.getX()), 
                computeY(Gdx.input.getY()),
                RECT_WIDTH, RECT_HEIGHT));
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
       if (this.rectangle.isPresent()) {
            this.rectangle.get().setPosition(computeX(screenX), computeY(screenY));
            return true;
       }
       return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
}
