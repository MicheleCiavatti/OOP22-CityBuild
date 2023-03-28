package it.unibo.view;


import java.io.File;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ShopScreen extends ScreenAdapter {
    private Stage stage;
    private Table table;
    private Label label;
    private Skin skin;

    public ShopScreen(Game game) {
        this.game = game;
        this.shopScreen = this;
    }

    public void create(){
        stage = new Stage();
        table = new Table();
        this.skin = new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json"));

        stage.addActor(table);
        label = new Label("Costruisci",skin);
        table.add(label);
        table.row();

        addOption("Legno", "10 monete");
        addOption("Acciaio", "20 monete");


    }

    public void render () {
        // Pulisci lo schermo
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Disegna lo stage
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }


    private void addOption(String option, String cost) {
        Label optionLabel = new Label(option, skin);
        Label costLabel = new Label(cost, skin);
        table.add(optionLabel);
        table.add(costLabel);
        table.row();
    }

    public void dispose () {
        // Rilascia le risorse
        stage.dispose();
        skin.dispose();
    }

    

   

}





