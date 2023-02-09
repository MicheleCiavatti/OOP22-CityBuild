package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.PlayerImpl;

public class TestPlayer {

    private final Player player = new PlayerImpl();

    @BeforeEach
    public void init() {
        Map<Resource, Integer> m = Map.of(Resource.WATER, 10, Resource.ENERGY, 5);
        player.addResources(m);
    }

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
}