package it.unibo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenExample implements Screen, InputProcessor {

    private static final int RECT_WIDTH = 150;
    private static final int RECT_HEIGHT = 225;
    private static final Rectangle NULL_RECTANGLE = new Rectangle(0, 0, 0, 0);

    private final ShapeRenderer shapeRenderer;
    //private final List<Rectangle> rectangles;
    private Optional<Rectangle> rectangle;

    public ScreenExample() {
        //this.rectangles = new ArrayList<>();
        this.shapeRenderer = new ShapeRenderer();
        rectangle = Optional.empty();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Mouse click");
        rectangle = Optional.of(new Rectangle(screenX - RECT_WIDTH / 2, Gdx.graphics.getHeight() - screenY - RECT_HEIGHT / 2, RECT_WIDTH, RECT_HEIGHT));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }


    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);
        drawRectangle(this.rectangle.orElse(NULL_RECTANGLE));
        shapeRenderer.end();
    }

    private void drawRectangle(Rectangle rectangle) {
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
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

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
}
