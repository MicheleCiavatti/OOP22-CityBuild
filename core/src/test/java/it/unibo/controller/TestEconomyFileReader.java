package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import org.junit.jupiter.api.Test;
import it.unibo.controller.api.EconomyFileReader;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.Resource;

/**A class to test the functioning of {@link it.unibo.controller.api.EconomyFileReader}. */
public class TestEconomyFileReader {

    private final EconomyFileReader economyFileReader = new EconomyFileReaderImpl();

    /**Testing the reading of the house.yml file.
     * Note that the test fails if house.yml file is changed without changing the Maps in TestHouse.
     */
    @Test
    public void testHouse() {
        var tables = economyFileReader.getSimpleEconomyTables(Resource.CITIZEN);
        assertEquals(tables.get(0), Map.of(Resource.CITIZEN, 10));
        assertEquals(tables.get(1), Map.of(Resource.METAL, 5, Resource.WOOD, 7));
    }
}