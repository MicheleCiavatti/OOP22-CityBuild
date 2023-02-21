package it.unibo;
import java.util.ArrayList;

import java.util.List;




import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BuildingsChoiceMenu extends ScreenAdapter implements ApplicationListener {

    private Stage stage;
    private String selectedBuildingName;
    private static final String EXTENSION = ".png";
    private boolean isResizing;
    private int index = 0;
    private final List<ImageButton> buttonList = new ArrayList<>();


    private ImageButton addButton(float x, float y, float width, float height, String imagePath, String buildingName){
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
                /*only test*/
                System.out.println("Selected building: " + selectedBuildingName);
                /*end test */
            }
        });
        return button;
    }


    @Override
    public void dispose() {
        stage.dispose(); 
    }

 /*   @Override
    public void create() {
        /*stage = new Stage();
        
        addButton(0, 0, 100, 100, "./desktop/bin/main/badlogic.jpg", "button1");
        addButton(0, 110, 100, 100, "./desktop/bin/main/badlogic.jpg", "button2");
        addButton(0, 220, 100, 100, "./desktop/bin/main/badlogic.jpg", "button3");

        Gdx.input.setInputProcessor(stage);*/


 /*       
        stage = new Stage();
        float buttonWidth = 100;
        float buttonHeight = 100;
        float buttonSpacing = 10;
        float buttonY = (Gdx.graphics.getHeight() - buttonHeight * 3 - buttonSpacing * 2) / 2;
    
        addButton((Gdx.graphics.getWidth() - buttonWidth) / 2, buttonY, buttonWidth, buttonHeight, "./desktop/bin/main/badlogic.jpg", "button1");
        addButton((Gdx.graphics.getWidth() - buttonWidth) / 2, buttonY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight, "./desktop/bin/main/badlogic.jpg", "button2");
        addButton((Gdx.graphics.getWidth() - buttonWidth) / 2, buttonY + (buttonHeight + buttonSpacing) * 2, buttonWidth, buttonHeight, "./desktop/bin/main/badlogic.jpg", "button3");
        addButton((Gdx.graphics.getWidth() - buttonWidth) / 2, buttonY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight, "./desktop/bin/main/badlogic.jpg", "button2");
        addButton((Gdx.graphics.getWidth() - buttonWidth) / 2, buttonY + (buttonHeight + buttonSpacing) * 2, buttonWidth, buttonHeight, "./desktop/bin/main/badlogic.jpg", "button3");


        Gdx.input.setInputProcessor(stage);
}*/

    @Override
    public void create() {
        stage = new Stage();
        float buttonWidth = 100;
        float buttonHeight = 100;
        float buttonSpacing = 10;
        float buttonY = (Gdx.graphics.getHeight() - buttonHeight * 3 - buttonSpacing * 2) / 2;
        
        
        for (int i = 0; i <= 10; i++) {
            buttonList.add(addButton((Gdx.graphics.getWidth() - buttonWidth) / 2, buttonY, buttonWidth, buttonHeight, "./desktop/bin/main/badlogic.jpg", "button1"));
            buttonY += buttonHeight + buttonSpacing;
        }

        System.out.println(buttonList.size());


        Gdx.input.setInputProcessor(stage);
    }

    private void selectButton(int index){
        //imposta il colore di tutti i bottoni a bianco
        for (ImageButton button : buttonList) {
            button.setColor(Color.WHITE);
        }
        //imposta il colore del bottone selezionato a rosso
        buttonList.get(index).setColor(Color.RED);

    }

    @Override
    public void show(){
        this.stage = new Stage(new ScreenViewport());
        this.stage.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch(keycode){
                    case Input.Keys.UP -> index++;
                    case Input.Keys.DOWN -> index--;
                    case Input.Keys.ENTER -> System.out.println(index);
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        if (!isResizing) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
        
    }

    @Override
    public void resize(int width, int height) {
        isResizing = true;
        stage.getViewport().update(width, height, true);
        float buttonWidth = 100;
        float buttonHeight = 100;
        float buttonSpacing = 10;
        float buttonY = (height - buttonHeight * 3 - buttonSpacing * 2) / 2;

        // Aggiorna la posizione dei bottoni
        for (Actor actor : stage.getActors()) {
            if (actor instanceof ImageButton) {
                ImageButton button = (ImageButton) actor;
                float buttonX = (width - buttonWidth) / 2;
                button.setPosition(buttonX, buttonY);
                buttonY += buttonHeight + buttonSpacing;
            }
        }
        isResizing = false;

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }



    
}





