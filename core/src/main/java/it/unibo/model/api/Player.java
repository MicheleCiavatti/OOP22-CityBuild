package it.unibo.model.api;

import java.util.Map;

public interface Player {
    
    public int getResourceAvailable(final Resource r);

    public Map<Resource, Integer> getAllResourcesAvailable();

    public boolean spendResources(final Map<Resource, Integer> toSpend);
}
