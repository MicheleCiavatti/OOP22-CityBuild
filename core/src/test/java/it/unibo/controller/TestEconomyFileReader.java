package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import it.unibo.controller.api.EconomyFileReader;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.Resource;

/**A class to test the functioning of {@link it.unibo.controller.api.EconomyFileReader}. */
public class TestEconomyFileReader {

    private final EconomyFileReader economyFileReader = new EconomyFileReaderImpl();

    /**Testing the reading of the house.yml file.
     * 
     */
    @Test
    public void testHouse() {
        var tables = economyFileReader.getSimpleEconomyTables(Resource.CITIZEN);
<<<<<<< HEAD
        System.out.println(tables);
        assertNotNull(tables);
        assertEquals(tables.size(), 3);
=======
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
>>>>>>> feature-building-mac
    }
}