package it.unibo.model.api;

import java.util.Map;

public interface Building {

    public int getResource();

    public Map<Resource, Integer> getCostUpgrade();
    
    public void upgrade(final Map<Resource, Integer> resourcesForUpgrade);

}