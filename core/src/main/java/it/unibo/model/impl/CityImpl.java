package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import it.unibo.model.api.City;
import it.unibo.model.api.Player;
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
    private static final Map<Resource, Integer> NULL_MAP = Map.of(Resource.CITIZEN, 0);
    private static final int CITIZENS_TO_ADD = 1;
    private static final int CITIZENS_TO_LOSE = 2;

    private final Player player;
    private final List<ProductionBuilding> buildings;
    private int citizens;

    public CityImpl(final Player player) {
        this.player = player;
        this.player.addResources(START_RESOURCES);
        this.buildings = new ArrayList<>();
        this.citizens = 0;
    }

    /**{@inheritDoc} */
    @Override
    public boolean build(ProductionBuilding building) {
        if (this.player.spendResources(building.getCostConstruction())) {
            this.operations(true, building);
            return true;
        }
        return false;
    }

    /**{@inheritDoc} */
    @Override
    public void demolish(ProductionBuilding building) {
        final var toDel1 = this.firstSatisfying(building, Predicate.not(ProductionBuilding::isUpgradable));
        final var toDel2 = this.firstSatisfying(building, b -> true);
        if (toDel1.isPresent()) {
            this.operations(false, toDel1.get());
        } else if (toDel2.isPresent()) {
            this.operations(false, toDel2.get());
        }
    }

    /**{@inheritDoc} */
    @Override
    public boolean upgrade(ProductionBuilding building) {
        final var toUp = this.firstSatisfying(building, ProductionBuilding::isUpgradable);
        if (toUp.isPresent()) {
            final var out = toUp.get().upgrade(this.player.getAllResources()) && 
                this.player.spendResources(building.getCostUpgrade());
            if (out && building.getName().equals("House") || building.getName().equals("Skyscraper")) {
                this.player.spendResources(building.getRevenue());
                this.player.addResources(toUp.get().getRevenue());
            }
            return out;
        }
        return false;
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getPlayerResources() {
        return Map.copyOf(this.player.getAllResources());
    }

    /**{@inheritDoc} */
    @Override
    public int getCitizens() {
        return this.citizens;
    }

    /**{@inheritDoc} */
    @Override
    public List<ProductionBuilding> getBuildings() {
        return List.copyOf(this.buildings);
    }

    @Override
    public void doCycle() {
        //Adding the revenue of every building built to the player resources
        this.buildings.forEach(b -> this.player.addResources(b.getRevenue().entrySet().stream()
            .filter(entry -> entry.getKey() != Resource.CITIZEN)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
        //The line above doesn't allow to add Resources regarding Citizen multiple times.
        if (this.player.spendResources(this.citizensCost())) {
            this.citizens = this.citizens + CITIZENS_TO_ADD > this.player.getResource(Resource.CITIZEN)
                ? this.player.getResource(Resource.CITIZEN)
                : this.citizens + CITIZENS_TO_ADD;
        } else {
            //Spends all the resources it can for the citizens cost, then loses citizens.
            this.player.spendResources(this.citizensCost().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Math.min(e.getValue(), this.player.getResource(e.getKey())))));
            this.citizens = Math.max(0, this.citizens - CITIZENS_TO_LOSE);
        }
    }

    private final Map<Resource, Integer> citizensCost() {
        return COST_PER_CITIZEN.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * this.citizens));
    }
    
    /*The Resource.CITIZEN is special because it holds the capacity of the city: therefore, the revenue regarding CITIZEN
     * must be added immediately in the player resources when the building is built, and if the building providing that revenue 
     * is destroyed than it must be subtracted immediately in the player resources.
     */
    private void operations(final boolean adding, final ProductionBuilding b) {
        if (adding) {
            this.buildings.add(b);
            this.player.addResources(this.checkForCitizens(b.getRevenue()));
        } else {
            this.buildings.remove(b);
            this.player.spendResources(this.checkForCitizens(b.getRevenue()));
        }
    }

    private Map<Resource, Integer> checkForCitizens(final Map<Resource, Integer> revenue) {
        return revenue.containsKey(Resource.CITIZEN)
            ? Map.of(Resource.CITIZEN, revenue.get(Resource.CITIZEN))
            : NULL_MAP;
    }

    private Optional<ProductionBuilding> firstSatisfying(final ProductionBuilding building, final Predicate<ProductionBuilding> condition) {
        return this.buildings.stream()
            .filter(b -> condition.test(b) && b.getName().equals(building.getName()))
            .findFirst();
    }
}
