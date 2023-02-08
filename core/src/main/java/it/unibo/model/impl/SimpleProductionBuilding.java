package it.unibo.model.impl;

import java.util.Map;
import java.util.stream.Collectors;
import it.unibo.model.api.EconomyHandler;
import it.unibo.model.api.EconomyHandlerFactory;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;

/**The class is an implementation of {@link it.unibo.model.api.ProductionBuilding} that models only Simple buildings.
 * The software has both Simple and Advanced building.
 */
public class SimpleProductionBuilding implements ProductionBuilding {

    private static final int MULTIPLIER = 2;
    private final Map<Resource, Integer> upgradeCost;
    private final Map<Resource, Integer> revenue;
    private final Map<Resource, Integer> constructionCost;
    private boolean upgradable;

    public SimpleProductionBuilding(final Resource r) {
        EconomyHandlerFactory economyHandlerFactory = new EconomyHandlerFactoryImpl();
        EconomyHandler economyHandler = economyHandlerFactory.createEconomyHandler();
        this.upgradeCost = this.removeEmptyResources(economyHandler.getSimpleUpgradeTable(r));
        this.revenue = this.removeEmptyResources(economyHandler.getSimpleRevenueTable(r));
        this.constructionCost = this.removeEmptyResources(economyHandler.getSimpleCostTable(r));
        this.upgradable = true;
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getRevenue() {
        return Map.copyOf(this.revenue);
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getCostUpgrade() {
        return Map.copyOf(this.upgradeCost);
    }

    /**{@inheritDoc} */
    @Override
    public void upgrade(Map<Resource, Integer> resourcesForUpgrade) {
        if (this.upgradable && this.upgradeCost.equals(this.removeEmptyResources(resourcesForUpgrade)) ) {
            this.revenue.replaceAll((key, value) -> value * MULTIPLIER);
            this.upgradable = false;
        }
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getCostConstruction() {
        return Map.copyOf(this.constructionCost);
    }

    //A transformation so that keys with value = 0 are removed, allowing for a safe compare between maps.
    private Map<Resource, Integer> removeEmptyResources(final Map<Resource, Integer> m) {
        return m.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
