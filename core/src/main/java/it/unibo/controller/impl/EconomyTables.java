package it.unibo.controller.impl;

import java.util.Map;

/**A simple class that models the 3 types of economy tables, mainly used by {@link it.unibo.controller.impl.EconomyFileReaderImpl}.*/
public class EconomyTables {
        
    private Map<String, Integer> revenue;
    private Map<String, Integer> construction;
    private Map<String, Integer> upgrade;

    public Map<String, Integer> getRevenue() {
        return revenue;
    }

    public void setRevenue(Map<String, Integer> revenue) {
        this.revenue = revenue;
    }

    public Map<String, Integer> getConstruction() {
        return construction;
    }

    public void setConstruction(Map<String, Integer> construction) {
        this.construction = construction;
    }

    public Map<String, Integer> getUpgrade() {
        return upgrade;
    }
    
    public void setUpgrade(Map<String, Integer> upgrade) {
        this.upgrade = upgrade;
    }
    
}
