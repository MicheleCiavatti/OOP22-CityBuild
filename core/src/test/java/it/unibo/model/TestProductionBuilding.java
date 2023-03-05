package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import it.unibo.controller.api.EconomyFileReader;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.BuildingFactory;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.BuildingFactoryImpl;

public class TestProductionBuilding {
    
    private static final Map<Resource, Integer> EMPTY_RESOURCES = Map.of(
        Resource.WATER, 0, Resource.WOOD, 0, Resource.ENERGY, 0,
        Resource.METAL, 0, Resource.CITIZEN, 0, Resource.GOLD, 0
    );
    private final BuildingFactory factory = new BuildingFactoryImpl();
    private final EconomyFileReader fileReader = new EconomyFileReaderImpl();

    @Test
    public void TestHouse() {
        final ProductionBuilding house = factory.createSimpleProductionBuilding(Resource.CITIZEN);
        final List<Map<Resource, Integer>> economyTables = fileReader.getSimpleEconomyTables(Resource.CITIZEN);
        assertEquals("House", house.getName());
        this.checkWithTables(economyTables, house);
        assertFalse(house.upgrade(EMPTY_RESOURCES));
    }

    @Test
    public void testMineralStation() {
        final ProductionBuilding mineralStation = factory.createAdvancedProductionBuilding(Resource.GOLD);
        final List<Map<Resource, Integer>> economyTables = fileReader.getAdvancedEconomyTables(Resource.GOLD);
        assertEquals("Mineral station", mineralStation.getName());
        this.checkWithTables(economyTables, mineralStation);
        assertFalse(mineralStation.upgrade(EMPTY_RESOURCES));
    }

    @Test
    public void testUltrafiltrationComplex() {
        final ProductionBuilding ultrafiltrationComplex = factory.createAdvancedProductionBuilding(Resource.WATER);
        final List<Map<Resource, Integer>> economyTables = fileReader.getAdvancedEconomyTables(Resource.WATER);
        assertEquals("Ultrafiltration complex", ultrafiltrationComplex.getName());
        this.checkWithTables(economyTables, ultrafiltrationComplex);
        assertFalse(ultrafiltrationComplex.upgrade(EMPTY_RESOURCES));
    }

    private void checkWithTables(final List<Map<Resource, Integer>> tables, final ProductionBuilding building) {
        assertEquals(equalizeMap(tables.get(0)), building.getRevenue());
        assertEquals(equalizeMap(tables.get(1)), building.getCostConstruction());
        assertEquals(equalizeMap(tables.get(2)), building.getCostUpgrade());
    }

    private Map<Resource, Integer> equalizeMap(final Map<Resource, Integer> m) {
        return Arrays.stream(Resource.values())
            .collect(Collectors.toMap(r -> r, r -> m.containsKey(r) ? m.get(r) : 0));
    }
}
