package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Map;


import org.junit.jupiter.api.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.model.api.City;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.api.Shop;
import it.unibo.model.impl.CityImpl;
import it.unibo.model.impl.PlayerImpl;
import it.unibo.model.impl.ShopImpl;

public class TestShop {


    private Map<Resource, Integer> RESOURCES = Map.of(
        Resource.CITIZEN, 5, Resource.ENERGY, 25, Resource.GOLD, 500,
        Resource.METAL, 25, Resource.WATER, 50, Resource.WOOD, 50
    );
    private Player p = new PlayerImpl();
    private City city;
    private Controller controller;
    private Shop shop = new ShopImpl(controller);

    @Test
    public void generateResource(){
        assertInstanceOf(String.class, shop.generateResource());
    }
    
}
