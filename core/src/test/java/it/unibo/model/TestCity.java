package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.BuildingFactory;
import it.unibo.model.api.City;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.BuildingFactoryImpl;
import it.unibo.model.impl.CityImpl;
import it.unibo.model.impl.PlayerImpl;

public class TestCity {
    
    private static final Map<Resource, Integer> MANY_RESOURCES = Map.of(
        Resource.CITIZEN, 1000, Resource.ENERGY, 1000, Resource.GOLD, 1000,
        Resource.METAL, 1000, Resource.WATER, 1000, Resource.WOOD, 1000
    );
    private Player p;
    private City city;
    private final BuildingFactory factory = new BuildingFactoryImpl();
    private final Resource FIRST_RES = Resource.METAL;

    @BeforeEach
    public void init() {
        this.p = new PlayerImpl();
        this.city = new CityImpl(this.p);
    }

    @Test
    public void testStart() {
        assertEquals(0, this.city.getBuildings().size());
        assertEquals(CityImpl.START_RESOURCES, this.city.getPlayerResources());
        assertEquals(0, this.city.getCitizens());
    }
}
