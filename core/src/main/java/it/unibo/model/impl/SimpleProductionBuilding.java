package it.unibo.model.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;

public class SimpleProductionBuilding implements ProductionBuilding {

    private static final int MULTIPLIER = 2;
    private final Map<Resource, Integer> upgradeCost;
    private final Map<Resource, Integer> revenue;
    private final Map<Resource, Integer> constructionCost;

    public SimpleProductionBuilding(final Resource r) {
        //TODO: switch for setting this.resource
        var economyHandler = new EconomyHandlerFactory().createEconomyHandler(r);
        this.upgradeCost = economyHandler.getSimpleUpgradeTable();
        this.revenue = economyHandler.getSimpleRevenueTable();
        this.constructionCost = economyHandler.getSimpleCostTable();
    }

    @Override
    public Map<Resource, Integer> getRevenue() {
        return this.revenue;
    }

    @Override
    public Map<Resource, Integer> getCostUpgrade() {
        // TODO: create map for setting cost upgrade
        return null;
    }

    @Override
    public void upgrade(Map<Resource, Integer> resourcesForUpgrade) {
        //TODO: check if the argument is valid for upgrading
        this.revenue.replaceAll((key, value) -> value * MULTIPLIER);
    }
}
