package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.controller.api.EconomyFileReader;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.BuildingFactory;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.BuildingFactoryImpl;

public class TestProductionBuilding {
    
    private final BuildingFactory factory = new BuildingFactoryImpl();
    private final EconomyFileReader fileReader = new EconomyFileReaderImpl();

    @Test
    public void TestHouse() {
        final ProductionBuilding house = factory.createSimpleProductionBuilding(Resource.CITIZEN);
        final List<Map<Resource, Integer>> economyTables = fileReader.getSimpleEconomyTables(Resource.CITIZEN);
        assertEquals("House", house.getName());
        assertEquals(economyTables.get(0), house.getRevenue());
        assertEquals(economyTables.get(1), house.getCostConstruction());
        assertEquals(economyTables.get(2), house.getCostUpgrade());
    }

    @Test
    public void testMineralStation() {
        final ProductionBuilding mineralStation = factory.createAdvancedProductionBuilding(Resource.GOLD);
        final List<Map<Resource, Integer>> economyTables = fileReader.getAdvancedEconomyTables(Resource.GOLD);
        assertEquals("Mineral station", mineralStation.getName());
        assertEquals(economyTables.get(0), mineralStation.getRevenue());
        assertEquals(economyTables.get(1), mineralStation.getCostConstruction());
        assertEquals(economyTables.get(2), mineralStation.getCostUpgrade());
    }
}
