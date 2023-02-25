package it.unibo.controller.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.controller.api.Controller;
import it.unibo.model.api.BuildingFactory;
import it.unibo.model.api.City;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.BuildingFactoryImpl;

/**A basic implementation of {@link it.unibo.controller.api.Controller}. */
public class ControllerImpl implements Controller {

    private static final Set<String> SIMPLE_BUILDINGS = 
        Set.of("depurator", "foundry", "house", "mine", "power_plant", "woodcutter");
    private static final Set<String> ADVANCED_BUILDINGS =
        Set.of("ultrafiltration_complex", "forge", "lumber_refinary", "mineral_station", "quantum_reactor", "skyscraper");
    
    private final City city;
    private final BuildingFactory factory;

    public ControllerImpl(final City city) {
        this.city = city;
        this.factory = new BuildingFactoryImpl();
    }

    @Override
    public boolean checkResourcesAndBuild(String buildingName) {
        return this.city.attemptBuild(this.fromNameToBuilding(buildingName));
    }

    @Override
    public void removeBuilding(String buildingName) {
        this.city.removeBuilding(this.fromNameToBuilding(buildingName));
    }

    @Override
    public boolean upgradeBuilding(String buildingName) {
        return this.city.upgradeBuilding(this.fromNameToBuilding(buildingName));
    }

    @Override
    public int getCitizensInTown() {
        return 0;
    }

    @Override
    public void doCycle() {
        
    }

    private ProductionBuilding fromNameToBuilding(final String name) {
        if (this.simpleOrAdvanced(name)) {
            return this.factory.createSimpleProductionBuilding(switch(name.toLowerCase()) {
                case "depurator" -> Resource.WATER;
                case "foundry" -> Resource.METAL;
                case "house" -> Resource.CITIZEN;
                case "mine" -> Resource.GOLD;
                case "power_plant" -> Resource.ENERGY;
                case "woodcutter" -> Resource.WOOD;
                default -> throw new IllegalStateException();
            });
        }
        return this.factory.createAdvancedProductionBuilding(switch(name.toLowerCase()) {
            case "ultrafiltration_complex" -> Resource.WATER;
            case "forge" -> Resource.METAL;
            case "lumber_refinary" -> Resource.WOOD;
            case "mineral_station" -> Resource.GOLD;
            case "quantum_reactor" -> Resource.ENERGY;
            case "skyscraper" -> Resource.CITIZEN;
            default -> throw new IllegalStateException();
        });
    }

    //True if the name is from a simple building, false otherwise.
    private boolean simpleOrAdvanced(final String name) {
        return SIMPLE_BUILDINGS.contains(name.toLowerCase());
    }
}
