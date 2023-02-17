package it.unibo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BuildingsChoiceMenu implements Screen {
    private Stage stage;

    public BuildingsChoiceMenu(){
        
    }

    private void addButton(float x, float y, float width, float height, String imagePath){
        Texture iconTexture = new Texture(imagePath);
        TextureRegion icon = new TextureRegion(iconTexture);

        ImageButton button = new ImageButton(new TextureRegionDrawable(icon));
        stage.addActor(button);
        button.setPosition(x, y);
        button.setSize(width, height);
    }
    
}
