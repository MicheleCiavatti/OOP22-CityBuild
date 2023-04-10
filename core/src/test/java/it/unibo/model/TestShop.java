package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

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
    private Shop shop = new ShopImpl();
    private Controller controller;

    @Test
    public void getResource() {
        p.addResources(RESOURCES);
        city = new CityImpl(p);
        controller = new ControllerImpl(city);
        shop.createDialogShop(controller);
        assertInstanceOf(Controller.class, shop.getResource());

    }

    @Test
    public void generateResource(){
        assertInstanceOf(String.class, shop.generateResource());
    }
    
}
