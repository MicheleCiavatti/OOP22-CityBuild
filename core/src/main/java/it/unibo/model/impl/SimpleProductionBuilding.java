package it.unibo.model.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import it.unibo.controller.impl.EconomyFileReaderImpl;
import it.unibo.model.api.EconomyHandler;
import it.unibo.model.api.EconomyHandlerFactory;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;

public class SimpleProductionBuilding implements ProductionBuilding {

    private static final int MULTIPLIER = 2;
    private final Map<Resource, Integer> upgradeCost;
    private final Map<Resource, Integer> revenue;
    private final Map<Resource, Integer> constructionCost;

    public SimpleProductionBuilding(final Resource r) {
        //TODO: switch for setting this.resource
        EconomyHandlerFactory economyHandlerFactory = new EconomyHandlerFactoryImpl();
        EconomyHandler economyHandler = economyHandlerFactory.createEconomyHandler();
        this.upgradeCost = Map.copyOf(economyHandler.getSimpleUpgradeTable(r));
        this.revenue = Map.copyOf(economyHandler.getSimpleRevenueTable(r));
        this.constructionCost = Map.copyOf(economyHandler.getSimpleCostTable(r));
    }

    @Override
    public Map<Resource, Integer> getRevenue() {
        return Map.copyOf(this.revenue);
    }

    @Override
    public Map<Resource, Integer> getCostUpgrade() {
        return Map.copyOf(this.upgradeCost);
    }

    @Override
    public void upgrade(Map<Resource, Integer> resourcesForUpgrade) {
        //TODO: check if the argument is valid for upgrading
        this.revenue.replaceAll((key, value) -> value * MULTIPLIER);
    }
}
