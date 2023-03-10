package it.unibo.controller.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.unibo.controller.api.Controller;
import it.unibo.model.api.BuildingFactory;
import it.unibo.model.api.City;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.BuildingFactoryImpl;

public class ControllerImpl implements Controller {

    private final Set<ProductionBuilding> allBuildings;
    private final City city;
    private final BuildingFactory factory;

    public ControllerImpl(final City city) {
        this.allBuildings = new HashSet<>();
        final BuildingFactory factory = new BuildingFactoryImpl();
        Arrays.stream(Resource.values())
            .flatMap(res -> Stream.of(factory.createSimpleProductionBuilding(res), factory.createAdvancedProductionBuilding(res)))
            .forEach(allBuildings::add);
        this.city = city;
        this.factory = new BuildingFactoryImpl();
    }




    @Override
    public boolean checkResourcesAndBuild(String buildingName) {
        // TODO Auto-generated method stub
    }

    @Override
    public void removeBuilding(String buildingName) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean upgradeBuilding(String buildingName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getCitizensInTown() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
