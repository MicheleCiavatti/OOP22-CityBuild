package it.unibo.controller.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
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
    private static final Set<String> SIMPLE_BUILDINGS = Set.of("depurator", "foundry", "house", "mine", "power_plant", "woodcutter");

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



    private ProductionBuilding fromNameToBuilding(final String name) {
        if (this.simpleOrAdvanced(name)) {
            return this.factory.createSimpleProductionBuilding(switch(name.toLowerCase()) {
                case "depurator" -> Resource.WATER;
                case "foundry" -> Resource.METAL;
                case "house" -> Resource.CITIZEN;
                case "mine" -> Resource.GOLD;
                case "power plant" -> Resource.ENERGY;
                case "woodcutter" -> Resource.WOOD;
                default -> throw new IllegalStateException();
            });
        }
        return this.factory.createAdvancedProductionBuilding(switch(name.toLowerCase()) {
            case "ultrafiltration complex" -> Resource.WATER;
            case "forge" -> Resource.METAL;
            case "lumber refinary" -> Resource.WOOD;
            case "mineral station" -> Resource.GOLD;
            case "quantum reactor" -> Resource.ENERGY;
            case "skyscraper" -> Resource.CITIZEN;
            default -> throw new IllegalStateException();
        });
    }

    //True if the name is from a simple building, false otherwise.
    private boolean simpleOrAdvanced(final String name) {
        return SIMPLE_BUILDINGS.contains(name.toLowerCase());
    }
    
}
