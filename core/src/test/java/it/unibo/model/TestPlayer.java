package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.PlayerImpl;

/**A class to test the function of {@link it.unibo.model.impl.PlayerImpl}.*/
public class TestPlayer {

    private final Player player = new PlayerImpl();

    @BeforeEach
    public void init() {
        player.addResources(Map.of(Resource.WATER, 10, Resource.ENERGY, 5));
    }

    /**Tests all getter methods of the class */
    @Test
    public void testRetrieveResources() {
        assertEquals(player.getAllResourcesAvailable(), Map.of(Resource.WOOD, 0, 
                Resource.WATER, 10,
                Resource.ENERGY, 5,
                Resource.METAL, 0,
                Resource.CITIZEN, 0));
        assertEquals(player.getResourceAvailable(Resource.WATER), 10);
        assertEquals(player.getResourceAvailable(Resource.CITIZEN), 0);
    }

    /**Tests the method to add resources. The method is safe towards negative and positive integers. */
    @Test
    public void testAddResources() {
        player.addResources(Map.of(Resource.CITIZEN, -2, Resource.WATER, 5));
        assertEquals(player.getResourceAvailable(Resource.CITIZEN), 2);
        assertEquals(player.getResourceAvailable(Resource.WATER), 15);
    }

    /**Tests the method to remove resources and its internal check. The method is safe towards negative and positive integers. */
    @Test
    public void testRemoveResources() {
        player.spendResources(Map.of(Resource.WATER, -3, Resource.ENERGY, 2));
        assertEquals(7, player.getResourceAvailable(Resource.WATER));
        assertEquals(3, player.getResourceAvailable(Resource.ENERGY));
        assertFalse(player.spendResources(Map.of(Resource.CITIZEN, 100)));
        assertEquals(0, player.getResourceAvailable(Resource.CITIZEN));
    }
}