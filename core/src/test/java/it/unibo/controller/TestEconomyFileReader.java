package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import it.unibo.controller.api.EconomyFileReader;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.Resource;

public class TestEconomyFileReader {

    private final EconomyFileReader economyFileReader = new EconomyFileReaderImpl();

    @Test
    public void testHouse() {
        var tables = economyFileReader.getSimpleEconomyTables(Resource.CITIZEN);
        assertEquals(3, tables.size());
    }

    @Test
    public void testForge() {
        var tables = economyFileReader.getAdvancedEconomyTables(Resource.METAL);
        assertEquals(3, tables.size());
    }

    @Test
    public void testQuantumReactor() {
        var tables = economyFileReader.getAdvancedEconomyTables(Resource.ENERGY);
        assertEquals(3, tables.size());
    }
}