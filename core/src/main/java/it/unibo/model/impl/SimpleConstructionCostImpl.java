package it.unibo.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.model.api.Resource;
import it.unibo.model.api.Cost;

public class SimpleConstructionCostImpl implements Cost{
    private Map<Resource, Integer> costMap = new HashMap<>();
    @Override
    public void setCost(Map<Resource, Integer> cost) {
        this.costMap = cost;
    }

    @Override
    public Map<Resource, Integer> getCost() {
        return this.costMap;
    }
}
