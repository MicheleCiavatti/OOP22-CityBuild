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
        stage = new Stage();
        
        Texture icon1Texture = new Texture("icon1.png");
        TextureRegion icon1 = new TextureRegion(icon1Texture);

        Texture icon2Texture = new Texture("icon2.png");
        TextureRegion icon2 = new TextureRegion(icon1Texture);

        Texture icon3Texture = new Texture("icon3.png");
        TextureRegion icon3 = new TextureRegion(icon1Texture);

        ImageButton button1 = new ImageButton(new TextureRegionDrawable(icon1));
        ImageButton button2 = new ImageButton(new TextureRegionDrawable(icon2));
        ImageButton button3 = new ImageButton(new TextureRegionDrawable(icon3));

        
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);

        float buttonWidth = Gdx.graphics.getWidth() * 0.2f;
        float buttonHeight = Gdx.graphics.getHeight() * 0.2f;
        float spacing = Gdx.graphics.getWidth() * 0.1f;

        button1.setPosition(spacing, spacing);
        button1.setSize(buttonWidth, buttonHeight);

        button2.setPosition(spacing, spacing);
        button2.setSize(buttonWidth, buttonHeight);

        button3.setPosition(spacing, spacing);
        button3.setSize(buttonWidth, buttonHeight);

    }
    
}
