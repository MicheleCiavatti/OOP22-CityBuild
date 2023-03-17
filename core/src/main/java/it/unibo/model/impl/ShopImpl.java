package it.unibo.model.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.model.api.City;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.api.Shop;
import it.unibo.view.GameScreen;

public class ShopImpl implements Shop{

    private static final Set<String> SIMPLE_BUILDINGS = Set.of("depurator", "foundry", "house", "mine", "power_plant", "woodcutter");
    private final String[] buildingList = {"forge", "lumber_refinary", "mineral_station", "quantum_reactor", "skyscraper", "ultrafiltration_complex",
    "depurator", "foundry", "house","mine","power_plant", "woodcutter"};

    private int randomItem;
    private int randomPrice;
    private Map<Resource, Integer> resource;
    private Map<Resource,Integer> costResource;
    private String text;

    private boolean button;
    private Skin skin;
    
    private boolean shopBoolean;

    private Controller controller;

    public ShopImpl(Controller controller) {
        this.resource = new HashMap<>();
        this.costResource = new HashMap<>();
        this.button = false;
        this.shopBoolean = false;
        this.controller = controller;
    }

    @Override
    public Controller getResource() {
        //this.controller.getPlayerResources().replace(fromNameToBuilding(buildingList[randomItem]), 1);
        try {
            final Player p = new PlayerImpl();
            p.addResources(this.controller.getPlayerResources());
            p.spendResources(costResource);
            p.addResources(resource);

            System.out.println("p resources: "+ p.getAllResources());

            final City city = new CityImpl(p, p.getAllResources());

            System.out.println(city.getPlayerResources());

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
        this.text = this.generateString();
        return this.text;
    }

    @Override
    public Dialog createDialogShop() {
        this.skin = new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json"));
        Dialog dialog = new Dialog("Shop", skin){ 

            protected void result(Object object){
                System.out.println(controller.getPlayerResources());
                button = true;
            }
        };
        dialog.text(generateString());
        dialog.button("Ok", true);

        return dialog;
    }

    public Boolean isButtonClicked(){ 
        return this.button;
    }

    private void setButtonFalse() {
        this.button = false;
    }

    private void setResource() {
        this.resource.put(fromNameToBuilding(this.buildingList[this.randomItem]), 1);
        this.costResource.put(Resource.GOLD, this.randomPrice);
    }

    private String generateString() {
        this.randomItem = (int) (Math.random() * 8);
        this.randomPrice = (int) (Math.random() * 3);
        String item = " vuoi comprare "+ buildingList[randomItem]+" per "+ this.randomPrice + " " + Resource.GOLD +  "?";
        this.setResource();
        return item;
    }

    private Resource fromNameToBuilding(final String name) {
        if (this.simpleOrAdvanced(name)) {
            switch(name.toLowerCase()) {
                case "depurator": return Resource.WATER; 
                case "foundry": return Resource.METAL; 
                case "house": return Resource.CITIZEN;
                case "mine": return Resource.GOLD;
                case "power_plant": return Resource.ENERGY;
                case "woodcutter": return Resource.WOOD;
                default:throw new IllegalStateException("Name not appropriate");
            }
        }

        switch(name.toLowerCase()) {
            case "ultrafiltration_complex": return Resource.WATER;
            case "forge": return Resource.METAL;
            case "lumber_refinary": return Resource.WOOD;
            case "mineral_station": return Resource.GOLD;
            case "quantum_reactor": return Resource.ENERGY;
            case "skyscraper": return Resource.CITIZEN;
            default: throw new IllegalStateException("Name not appropriate");
        }
    }

    private boolean simpleOrAdvanced(final String name) {
        return SIMPLE_BUILDINGS.contains(name.toLowerCase());
    }

    @Override
    public Boolean isShopCalled() {
        return this.shopBoolean;
    }

    @Override
    public void setShopCalled(Boolean b) {
        this.shopBoolean = b;
    }

}
