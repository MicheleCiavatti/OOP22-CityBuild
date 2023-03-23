package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.BuildingFactory;
import it.unibo.model.api.Player;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.BuildingFactoryImpl;
import it.unibo.model.impl.CityImpl;
import it.unibo.model.impl.PlayerImpl;

public class TestCity {
    
    private static final Map<Resource, Integer> MANY_RESOURCES = Map.of(
        Resource.CITIZEN, 1000, Resource.ENERGY, 1000, Resource.GOLD, 1000,
        Resource.METAL, 1000, Resource.WATER, 1000, Resource.WOOD, 1000
    );
    private static final int CITIZENS_TO_ADD = 100;
    private Player p;
    private CityImpl city;
    private final BuildingFactory factory = new BuildingFactoryImpl();
    private final Resource FIRST_RES = Resource.METAL;

    @BeforeEach
    public void init() {
        this.p = new PlayerImpl();
        this.city = new CityImpl(this.p);
        this.city.addCitizens(CITIZENS_TO_ADD);
    }

    @Test
    public void testStart() {
        assertEquals(0, this.city.getBuildings().size());
        assertEquals(CityImpl.START_RESOURCES, this.city.getPlayerResources());
        this.city.addCitizens(- CITIZENS_TO_ADD);
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

    /**The test creates some buildings, upgrades half of them and destroys half + 1 of them. If implemented correctly, all the
     * buildings not demolished should be upgraded.*/
    @Test
    public void testRemoveNotUpgraded() {
        this.p.addResources(MANY_RESOURCES);
        final int iterations = 10;
        Stream.iterate(0, i -> i < iterations, i -> i + 1).forEach(i -> this.city.build(factory.createSimpleProductionBuilding(FIRST_RES)));
        Stream.iterate(0, i -> i < iterations / 2, i -> i + 1).forEach(i -> this.city.upgrade(factory.createSimpleProductionBuilding(FIRST_RES)));
        Stream.iterate(0, i -> i < iterations / 2 + 1, i -> i + 1).forEach(i -> this.city.demolish(factory.createSimpleProductionBuilding(FIRST_RES)));
        assertTrue(this.city.getBuildings().stream().allMatch(ProductionBuilding::isUpgradable));
    }

    /**The CITIZEN is a special resource: this method tests if it is treated as such. */
    @Test
    public void testCitizens() {
        this.p.spendResources(CityImpl.START_RESOURCES);
        this.addBuilding(Resource.CITIZEN);
        final var houseRevenue = factory.createSimpleProductionBuilding(Resource.CITIZEN).getRevenue().get(Resource.CITIZEN);
        final var skyscraperRevenue = factory.createAdvancedProductionBuilding(Resource.CITIZEN).getRevenue().get(Resource.CITIZEN);
        assertEquals(houseRevenue + skyscraperRevenue,this.city.getPlayerResources().get(Resource.CITIZEN));
        this.city.demolish(factory.createSimpleProductionBuilding(Resource.CITIZEN));
        assertEquals(skyscraperRevenue, this.city.getPlayerResources().get(Resource.CITIZEN));
    }

    @Test
    public void testCycle() {
        this.p.spendResources(CityImpl.START_RESOURCES);
        this.addBuilding(Resource.CITIZEN);
        this.addBuilding(FIRST_RES);
        final var simpleRevenue = factory.createSimpleProductionBuilding(FIRST_RES).getRevenue().get(FIRST_RES);
        final var advancedRevenue = factory.createAdvancedProductionBuilding(FIRST_RES).getRevenue().get(FIRST_RES);
        this.city.addCitizens(- CITIZENS_TO_ADD);
        assertTrue(this.removeCitizen(this.city.getPlayerResources()).values().stream().allMatch(value -> value == 0));
        this.city.doCycle();
        assertEquals(1, this.city.getCitizens());
        assertEquals(simpleRevenue + advancedRevenue, this.city.getPlayerResources().get(FIRST_RES));
        this.city.doCycle();
        assertEquals(0, this.city.getCitizens());
        assertEquals((simpleRevenue + advancedRevenue) * 2 - CityImpl.COST_PER_CITIZEN.get(FIRST_RES), 
            this.city.getPlayerResources().get(FIRST_RES));
    }

    //Removes the CITIZEN key and value from a Map if present
    private Map<Resource, Integer> removeCitizen(final Map<Resource, Integer> map) {
        return map.containsKey(Resource.CITIZEN)
            ? map.entrySet().stream()
                .filter(e -> e.getKey() != Resource.CITIZEN)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            : map;
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
