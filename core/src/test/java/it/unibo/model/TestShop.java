package it.unibo.model;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.PlayerImpl;

public class TestShop {
    Player p = new PlayerImpl();
    @BeforeEach
    public void init() {
        p.addResources(Map.of(Resource.WATER, 10, Resource.ENERGY, 5));
    }

    @Test
    public void testGetResource(){

    }

    /*@Test
    public void generateResource(){
        Shop s = new ShopImpl(null);
        s.generateResource();
    }*/
}
