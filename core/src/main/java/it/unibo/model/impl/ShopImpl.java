package it.unibo.model.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.model.api.City;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.api.Shop;

public class ShopImpl implements Shop{

    private final String[] resourceStringList = {"WATER", "WOOD", "ENERGY", "METAL", "CITIZEN"};

    private int randomAmount;
    private int randomResource;
    private int randomPrice;
    private Map<Resource, Integer> resource;
    private Map<Resource,Integer> costResource;
    private String text;

    private boolean button;
    private Skin skin;
    
    private boolean visibility;

    private Controller controller;

    public ShopImpl() {
        this.resource = new HashMap<>();
        this.costResource = new HashMap<>();
        this.button = false;
        this.visibility = false;
    }

    @Override
    public Controller getResource() {
        this.setResource();
        try {
            final Player p = new PlayerImpl();
            p.addResources(this.controller.getPlayerResources());
            p.spendResources(costResource);
            p.addResources(resource);

            final City city = new CityImpl(p, p.getAllResources());

			this.controller = new ControllerImpl(city);
            System.out.println("GetResource: " + this.controller.getPlayerResources());
            this.setButtonFalse();

        } catch (Exception e) {
            System.err.println(e);
        }
        return this.controller;
    }

    @Override
    public String generateResource() {
        this.randomAmount = (int)(Math.random() * 10 + 1);
        this.randomResource = (int) (Math.random() * 4);
        this.randomPrice = (int) (Math.random() * 50);
        text  = " vuoi comprare "+ randomAmount + " di " +  resourceStringList[randomResource]+" per "+ this.randomPrice + " " + Resource.GOLD +  "?";
        System.out.println(text);
        return text;
    }

    @Override
    public Dialog createDialogShop(Controller c) {
        this.controller = c;

        this.skin = new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json"));
        Dialog dialog = new Dialog("Shop", skin){ 

            protected void result(Object object){
                System.out.println("OK button: "+ controller.getPlayerResources());

                if((Boolean) object) {
                    button=true;
                }
            }
        };

        dialog.text(generateResource());
        dialog.button("Ok", true);
        dialog.button("NO", false); 

        return dialog;
    }

    public Boolean isButtonClicked(){ 
        return this.button;
    }

    private void setButtonFalse() {
        this.button = false;
    }

    private void setResource() {
        System.out.println("setResource(): " + Resource.valueOf(Resource.class, resourceStringList[randomResource]) +" " + randomAmount);
        this.resource.put(Resource.valueOf(Resource.class, resourceStringList[randomResource]), randomAmount);
        System.out.println("CostResource " + Resource.GOLD  + " " + randomPrice);
        this.costResource.put(Resource.GOLD, this.randomPrice);
    }

    @Override
    public Boolean getVisibility() {
        return this.visibility;
    }

    @Override
    public void setVisibility(Boolean b) {
        this.visibility = b;
    }

}
