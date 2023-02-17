package it.unibo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BuildingsChoiceMenu {
    private Stage stage;

    private void addButton(float x, float y, float width, float height, String imagePath){
        Texture iconTexture = new Texture(imagePath);
        TextureRegion icon = new TextureRegion(iconTexture);

        ImageButton button = new ImageButton(new TextureRegionDrawable(icon));
        stage.addActor(button);
        button.setPosition(x, y);
        button.setSize(width, height);
    }

    public BuildingsChoiceMenu(){
        stage = new Stage();
        
        addButton(0, 0, 100, 100, "button1.png");
        addButton(120, 0, 100, 100, "button1.png");
        addButton(240, 0, 100, 100, "button1.png");
        addButton(0, 120, 100, 100, "button1.png");
        addButton(120, 120, 100, 100, "button1.png");
        addButton(240, 120, 100, 100, "button1.png");
        addButton(0, 240, 100, 100, "button1.png");
        addButton(0, 240, 100, 100, "button1.png");
        addButton(0, 240, 100, 100, "button1.png");

        Gdx.input.setInputProcessor(stage);
    } 

    public void show(){
        stage.act();
        stage.draw();
    }

    public void dispose(){
        stage.dispose();
    }

    
    
}
