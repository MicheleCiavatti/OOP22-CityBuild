package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        assertNotNull(tables);
        assertEquals(tables.size(), 3);
    }
}