package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testBuild() {
        assertTrue(this.p.spendResources(CityImpl.START_RESOURCES)); //Removing all resources from the city
        this.addBuilding(FIRST_RES);
        this.addBuilding(Resource.GOLD);
    }

    @Test
    public void testDemolish() {
        this.addBuilding(FIRST_RES);
        final var buildingRemoved = factory.createSimpleProductionBuilding(FIRST_RES);
        final var buildingNotRemoved = factory.createAdvancedProductionBuilding(FIRST_RES);
        this.city.demolish(buildingRemoved);
        assertEquals(1, this.city.getBuildings().size());
        assertTrue(this.city.getBuildings().stream().filter(b -> b.getName().equals(buildingRemoved.getName())).findFirst().isEmpty());
        assertTrue(this.city.getBuildings().stream().filter(b -> b.getName().equals(buildingNotRemoved.getName())).findFirst().isPresent());
    }

    @Test
    public void testUpgrade() {
        this.p.spendResources(CityImpl.START_RESOURCES); //Empties the resources of the player
        this.addBuilding(FIRST_RES);
        final var toUp = factory.createSimpleProductionBuilding(FIRST_RES);
        final var toUpButFail = factory.createAdvancedProductionBuilding(FIRST_RES);
        //The second will fail because there isn't enough resources for the upgrade
        this.p.addResources(toUp.getCostUpgrade());
        assertTrue(this.city.upgrade(toUp));
        assertFalse(this.city.upgrade(toUpButFail));
    }

    private void addBuilding(final Resource r) {
        final var simple = this.factory.createSimpleProductionBuilding(r);
        final var advanced = this.factory.createAdvancedProductionBuilding(r);
        this.p.addResources(simple.getCostConstruction());
        this.p.addResources(advanced.getCostConstruction());
        assertTrue(this.city.build(simple));
        assertTrue(this.city.build(advanced));
    }
}
