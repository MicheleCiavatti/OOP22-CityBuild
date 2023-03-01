package it.unibo.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import it.unibo.controller.api.EconomyFileReader;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.EconomyHandler;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.EconomyHandlerFactoryImpl;



public class TestEconomyHandler {
    private  EconomyHandler economyHandler= new EconomyHandlerFactoryImpl().createEconomyHandler();
    EconomyFileReader economyFileReader = new EconomyFileReaderImpl();

    @Test
    public void testSimple(){

        assertEquals(economyFileReader.getSimpleEconomyTables(Resource.CITIZEN).get(1), 
            economyHandler.getSimpleCostTable(Resource.CITIZEN));
        
        assertEquals(economyFileReader.getSimpleEconomyTables(Resource.CITIZEN).get(0), 
            economyHandler.getSimpleRevenueTable(Resource.CITIZEN));
        
        assertEquals(economyFileReader.getSimpleEconomyTables(Resource.CITIZEN).get(2),
            economyHandler.getSimpleUpgradeTable(Resource.CITIZEN));

    }   

        
    @Test
    public void testAdvanced(){
        assertEquals(economyFileReader.getAdvancedEconomyTables(Resource.CITIZEN).get(1),
            economyHandler.getAdvancedCostTable(Resource.CITIZEN));
        
        assertEquals(economyFileReader.getAdvancedEconomyTables(Resource.CITIZEN).get(0),
            economyHandler.getAdvancedRevenueTable(Resource.CITIZEN));
        
        assertEquals(economyFileReader.getAdvancedEconomyTables(Resource.CITIZEN).get(2),
            economyHandler.getAdvancedUpgradeTable(Resource.CITIZEN));
    }
}
