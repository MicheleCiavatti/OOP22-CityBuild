package it.unibo.model.impl;

import java.util.Map;

import it.unibo.controller.api.EconomyFileReader;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.EconomyHandler;
import it.unibo.model.api.Resource;

public class EconomyHandlerImpl implements EconomyHandler{

    private final EconomyFileReader fileReader;
    
    public EconomyHandlerImpl() {
        this.fileReader = new EconomyFileReaderImpl();
    }

    @Override
    public Map<Resource, Integer> getSimpleCostTable(Resource resource) {
        return this.fileReader.getSimpleEconomyTables(resource).get(1);
    }

    @Override
    public Map<Resource, Integer> getSimpleRevenueTable(Resource resource) {
        return this.fileReader.getSimpleEconomyTables(resource).get(0);
    }

    @Override
    public Map<Resource, Integer> getSimpleUpgradeTable(Resource resource) {
        return this.fileReader.getSimpleEconomyTables(resource).get(2);
    }

    @Override
    public Map<Resource, Integer> getAdvancedCostTable(Resource resource) {
        return this.fileReader.getAdvancedEconomyTables(resource).get(1);
    }

    @Override
    public Map<Resource, Integer> getAdvancedRevenueTable(Resource resource) {
        return this.fileReader.getAdvancedEconomyTables(resource).get(0);
    }

    @Override
    public Map<Resource, Integer> getAdvancedUpgradeTable(Resource resource) {
        return this.fileReader.getAdvancedEconomyTables(resource).get(2);
    }
    
}
