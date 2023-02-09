package it.unibo.model.impl;

import it.unibo.model.api.BuildingFactory;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;

/**An implementation of {@link it.unibo.model.api.BuildingFactory} */
public class BuildingFactoryImpl implements BuildingFactory {

    /**{@inheritDoc} */
    @Override
    public ProductionBuilding createSimpleProductionBuilding(final Resource resource) {
        return new ProductionBuildingImpl(resource, true);
    }
    
}
