package it.unibo.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameInterface implements ApplicationListener {

    private Stage stage;
    private String selectedBuildingName;
    private static final String EXTENSION = ".png";

    private void addButton(float x, float y, float width, float height, String imagePath, String buildingName){
        Texture iconTexture = new Texture(imagePath);
        TextureRegion icon = new TextureRegion(iconTexture);

        ImageButton button = new ImageButton(new TextureRegionDrawable(icon));
        button.setName(buildingName);
        stage.addActor(button);
        button.setPosition(x, y);
        button.setSize(width, height);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedBuildingName = buildingName+EXTENSION;
                System.out.println("Selected building: " + selectedBuildingName);
            }
        });
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume(){}
    

    @Override
    public void dispose() {
        stage.dispose(); 
    }

    @Override
    public void create() {
        stage = new Stage();
        
        addButton(0, 0, 100, 100, "./desktop/bin/main/badlogic.jpg", "button1");
        addButton(120, 0, 100, 100, "./desktop/bin/main/badlogic.jpg", "button2");
        addButton(240, 0, 100, 100, "./desktop/bin/main/badlogic.jpg", "button3");

        Gdx.input.setInputProcessor(stage);
        
    }

    @Override
    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
    } 
}





