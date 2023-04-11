package it.unibo.model.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.model.api.City;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.api.Shop;

/**This class is the implementation of the interface {@link it.unibo.model.api.Shop}
 * 
 */
public class ShopImpl implements Shop{

    private final String[] resourceStringList = {"WATER", "WOOD", "ENERGY", "METAL", "CITIZEN"};

    private int randomAmount;
    private int randomResource;
    private int randomPrice;
    private Map<Resource, Integer> resource;
    private Map<Resource,Integer> costResource;
    private String text;
    private boolean okButton;
    private boolean noButton;
    private Skin skin = new Skin(Gdx.files.internal("skin_flatEarth" + File.separator + "flat-earth-ui.json"));
    private boolean visibility;
    private Controller controller;

    public ShopImpl(Controller c) {
        this.resource = new HashMap<>();
        this.costResource = new HashMap<>();
        this.okButton = false;
        this.noButton = false;
        this.visibility = false;
        this.controller = c;
    }

    /**{@inheritDoc} */
    @Override
    public Controller getResource() {
        this.setResource();

        if(!isTransitionValid()) {
            System.out.println("Transition failed");
            return this.controller;
        }
        else {
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
    }

    /**{@inheritDoc} */
    @Override
    public String generateResource() {
        this.randomAmount = (int)(Math.random() * 10 + 1);
        this.randomResource = (int) (Math.random() * 4);
        this.randomPrice = (int) (Math.random() * 50);
        text  = " vuoi comprare "+ randomAmount + " di " +  resourceStringList[randomResource]+" per "+ this.randomPrice + " " + Resource.GOLD +  "?";
        System.out.println(text);
        return text;
    }

    /**{@inheritDoc} */
    @Override
    public Dialog createDialogShop(Controller c) {
        this.controller = c;
        
        if(!this.resource.isEmpty()) {
            this.resource.clear();
        }

        
        Dialog dialog = new Dialog("Shop", skin){ 

            protected void result(Object object){
                if(object.equals(1L)){
                    okButton = true;
                } else {
                    noButton = true;
                }
            }
        };

        dialog.text(generateResource());
        dialog.button("Ok", 1L);
        dialog.button("NO", false); 

        return dialog;
    }

    @Override
    public Boolean getVisibility() {
        return this.visibility;
    }

    @Override
    public void setVisibility(Boolean b) {
        this.visibility = b;
    }

    public Boolean isOkButtonClicked() { 
        return this.okButton;
    }

    public Boolean isNoButtonClicked() {
        return this.noButton;
    }

    private void setButtonFalse() {
        this.okButton = false;
        this.noButton = false;
    }

    /*
     * This method set the resource generated and the cost in the field in order to add it to the player's resources 
     */
    private void setResource() {
        this.resource.put(Resource.valueOf(Resource.class, resourceStringList[randomResource]), randomAmount);
        this.costResource.put(Resource.GOLD, this.randomPrice);

    }

    /*
     * This method checks whether the player has sufficient gold to proceed with the purchase
     */
    private Boolean isTransitionValid() {
        for (Entry<Resource,Integer> entry: this.controller.getPlayerResources().entrySet()) {
            if(entry.getKey().equals(Resource.GOLD)) {
                if(entry.getValue() < this.randomPrice){
                    return false;
                }
            }
        }

        return true;

    }

}
