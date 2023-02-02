package it.unibo.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.model.api.Resource;
import it.unibo.model.api.Cost;

public class UpgradeCostImpl implements Cost{
    Map<Resource, Integer> costUpgrade = new HashMap<>();
    @Override
    public void setCost(Map<Resource, Integer> cost) {
        this.costUpgrade = cost;
        
    }

    @Override
    public Map<Resource, Integer> getCost() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
