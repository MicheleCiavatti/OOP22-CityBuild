package it.unibo.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.model.api.Resource;
import it.unibo.model.api.Cost;

public class SimpleUpgradeCostImpl implements Cost{
    Map<Resource, Integer> costUpgrade = new HashMap<>();
    @Override
    public void setCost(Map<Resource, Integer> cost) {
        this.costUpgrade = cost;
        
    }

    @Override
    public Map<Resource, Integer> getCost() {
        return this.costUpgrade;
    }
    
}
