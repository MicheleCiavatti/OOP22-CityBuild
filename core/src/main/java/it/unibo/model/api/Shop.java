package it.unibo.model.api;


import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

import it.unibo.controller.api.Controller;

public interface Shop {
    /**
     * 
     * @return Map<Resource, Integer> of the resource generated casually by the shop
     */
    public Controller getResource();
    
    /**
     * Generates a random material to offer to the client
     */
    public String generateResource();

    public Dialog createDialogShop();

    public Boolean isButtonClicked();

    public Boolean isShopCalled();

    public void setShopCalled(Boolean b);
}
