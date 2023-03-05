package it.unibo.model.impl;

import java.util.List;
import java.util.Map;
import it.unibo.model.api.City;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;

public class CityImpl implements City {

    public static final Map<Resource, Integer> START_RESOURCES = Map.of(
        Resource.CITIZEN, 0, Resource.ENERGY, 50, Resource.GOLD, 50,
        Resource.METAL, 50, Resource.WATER, 50, Resource.WOOD, 50
    );
    public static final Map<Resource, Integer> COST_PER_CITIZEN = Map.of(
        Resource.ENERGY, 3, Resource.GOLD, 1, Resource.METAL, 2,
        Resource.WATER, 5, Resource.WOOD, 2
    );

    @Override
    public boolean build(ProductionBuilding building) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'build'");
    }

    @Override
    public void demolish(ProductionBuilding building) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'demolish'");
    }

    @Override
    public boolean upgrade(ProductionBuilding building) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upgrade'");
    }

    @Override
    public Map<Resource, Integer> getPlayerResources() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerResources'");
    }

    @Override
    public int getCitizens() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCitizens'");
    }

    @Override
    public List<ProductionBuilding> getBuildings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBuildings'");
    }

    @Override
    public void doCycle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doCycle'");
    }
    
}
