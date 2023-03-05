package it.unibo.model.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import it.unibo.model.api.EconomyHandler;
import it.unibo.model.api.EconomyHandlerFactory;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;

/**The class is an implementation of {@link it.unibo.model.api.ProductionBuilding} that models Production buildings.
 * The software has both Simple and Advanced production building.
 */
public class ProductionBuildingImpl implements ProductionBuilding {

    private static final int MULTIPLIER = 2;
    private final Map<Resource, Integer> upgradeCost;
    private final Map<Resource, Integer> revenue;
    private final Map<Resource, Integer> constructionCost;
    private final String name;
    private boolean upgradable;

    public ProductionBuildingImpl(final Resource r, final boolean isSimpleBuilding) {
        EconomyHandlerFactory economyHandlerFactory = new EconomyHandlerFactoryImpl();
        EconomyHandler tables = economyHandlerFactory.createEconomyHandler();
        if (isSimpleBuilding) {
            this.revenue = this.equalizeMap(tables.getSimpleRevenueTable(r));
            this.constructionCost = this.equalizeMap(tables.getSimpleCostTable(r));
            this.upgradeCost = this.equalizeMap(tables.getSimpleUpgradeTable(r));
            this.name = r.getSimpleBuilding().replace("_", " ");
        } else {
            this.revenue = this.equalizeMap(tables.getAdvancedRevenueTable(r));
            this.constructionCost = this.equalizeMap(tables.getAdvancedCostTable(r));
            this.upgradeCost = this.equalizeMap(tables.getAdvancedUpgradeTable(r));
            this.name = r.getAdvancedBuilding().replace("_", " ");
        }
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
    public boolean upgrade(Map<Resource, Integer> resourcesForUpgrade) {
        if (this.upgradable && this.enoughResources(resourcesForUpgrade, this.upgradeCost)) {
            this.revenue.replaceAll((key, value) -> value * MULTIPLIER);
            this.upgradable = false;
            return true;
        }
        return false;
    }

    private boolean enoughResources(final Map<Resource, Integer> resources, final Map<Resource, Integer> cost) {
        return resources.entrySet().stream().allMatch(e -> e.getValue() >= cost.get(e.getKey()));
    }

    /**{@inheritDoc} */
    @Override
    public String getName() {
        return this.name;
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getCostConstruction() {
        return Map.copyOf(this.constructionCost);
    }

    //A transformation to map so that if a Resource is not present, is added with value 0.
    private Map<Resource, Integer> equalizeMap(final Map<Resource, Integer> m) {
        return Arrays.stream(Resource.values())
            .collect(Collectors.toMap(r -> r, r -> m.containsKey(r) ? m.get(r) : 0));
    }

    @Override
    public boolean isUpgradable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUpgradable'");
    }

    
}
