package it.unibo.model.impl;

import java.util.Map;

import it.unibo.controller.api.EconomyFileReader;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.EconomyHandler;
import it.unibo.model.api.Resource;

/** This class is the implementation of the interface {@link it.unibo.model.api.EconomyHandler}. Model of the economy system
 * 
 */
public class EconomyHandlerImpl implements EconomyHandler{

    private final EconomyFileReader fileReader;
    
    public EconomyHandlerImpl() {
        this.fileReader = new EconomyFileReaderImpl();
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getSimpleCostTable(Resource resource) {
        return this.fileReader.getSimpleEconomyTables(resource).get(1);
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getSimpleRevenueTable(Resource resource) {
        return this.fileReader.getSimpleEconomyTables(resource).get(0);
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getSimpleUpgradeTable(Resource resource) {
        return this.fileReader.getSimpleEconomyTables(resource).get(2);
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getAdvancedCostTable(Resource resource) {
        return this.fileReader.getAdvancedEconomyTables(resource).get(1);
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getAdvancedRevenueTable(Resource resource) {
        return this.fileReader.getAdvancedEconomyTables(resource).get(0);
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getAdvancedUpgradeTable(Resource resource) {
        return this.fileReader.getAdvancedEconomyTables(resource).get(2);
    }
    
}
