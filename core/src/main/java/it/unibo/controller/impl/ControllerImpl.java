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

/**A basic implementation of {@link it.unibo.controller.api.Controller}. */
public class ControllerImpl implements Controller {

    private static final Set<String> SIMPLE_BUILDINGS = 
        Set.of("depurator", "foundry", "house", "mine", "power_plant", "woodcutter");
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
    public Map<Resource, Integer> getPlayerResources() {
        return this.city.getPlayerResources();
    }

    @Override
    public Map<Resource, Integer> getCost(final String buildingName) {
        return this.getTable(buildingName, 1);
    }

    @Override
    public Map<Resource, Integer> getUpgrade(String buildingName) {
        return this.getTable(buildingName, 2);
    }

    @Override
    public Map<Resource, Integer> getRevenue(String buildingName) {
        return this.getTable(buildingName, 0);
    }

    //idTable = 0 means revenue, idTable = 1 means construction, idTable = 2 means upgrade.
    private Map<Resource, Integer> getTable(final String name, final int idTable) {
        final Optional<ProductionBuilding> building = this.allBuildings.stream().filter(b -> b.getName().equals(name)).findFirst();
        if (building.isEmpty()) {
            throw new IllegalArgumentException("Wrong parameter in 'getTable' in class ControllerImpl");
        }
        return switch(idTable) {
            case 0 -> building.get().getRevenue();
            case 1 -> building.get().getCostConstruction();
            case 2 -> building.get().getCostUpgrade();
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public int getCitizensInTown() {
        return this.city.getCitizensInTown();
    }

    @Override
    public void doCycle() {
        this.city.doCycle();
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
