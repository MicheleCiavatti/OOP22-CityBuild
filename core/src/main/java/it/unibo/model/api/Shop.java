package it.unibo.model.api;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

import it.unibo.controller.api.Controller;

public interface Shop {
    /**
     * 
     * @return Controller of the resource generated casually by the shop
     */
    public Controller getResource();
    
    /**
     * Generates a random material to offer to the client
     */
    public String generateResource();

    /**
     * Creates the dialog of the shop
     * @param c is given to the method's in order to update the resource status of the player
     * @return Dialog
     */
    public Dialog createDialogShop(Controller c);

    /**
     * 
     * @return Boolean. True if Ok button is clicked, otherwise false if not clicked
     */
    public Boolean isOkButtonClicked();

    /**
     * 
     * @return Boolean. True if No is clicked, otherwise false if not clicked
     */
    public Boolean isNoButtonClicked();

    /**
     * This method return a boolean to the GameScreen to check if the dialog shop needs to show to the stage or not.
     * @return Boolean
     */
    public Boolean getVisibility();

    /**
     * This method sets the visibility of the dialog of the shop
     * @param b
     */
    public void setVisibility(Boolean b);
}
