package it.unibo.view;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import it.unibo.model.api.Player;
import it.unibo.model.api.Shop;
import it.unibo.model.impl.ShopImpl;

public class DialogShop {
    private Dialog dialog;
    private Skin skin;
    private Shop shop;

    public DialogShop (){
    }
    public DialogShop(Player p){
    }

    public Dialog create() {
        this.skin = new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json"));
        
        this.dialog = new Dialog("Shop", skin) {
            protected void result(Object object){
                System.out.println("Button clicked");
            }
        };

        this.dialog.text(shop.generateResource());
        this.dialog.button("Ok", true);

        return this.dialog;
    }
}
