package it.unibo.model.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;

public class PlayerImpl implements Player {

    private final Map<Resource, Integer> resources;

    public PlayerImpl() {
        this.resources = new HashMap<>();
        Arrays.stream(Resource.values()).forEach(value -> this.resources.put(value, 0));
    }


    @Override
    public int getResourceAvailable(final Resource r) {
        return this.resources.get(r);
    }

    @Override
    public Map<Resource, Integer> getAllResourcesAvailable() {
        return Map.copyOf(this.resources);
    }

    @Override
    public boolean spendResources(Map<Resource, Integer> toSpend) {
        if (this.checkResourcesToSpend(toSpend)) {
            toSpend.entrySet().forEach(entry -> this.resources.replace(entry.getKey(), this.resources.get(entry.getKey()) - entry.getValue()));
            return true;
        }
        return false;
    }

    private boolean checkResourcesToSpend(Map<Resource, Integer> toSpend) {
        return toSpend.entrySet()
            .stream()
            .allMatch(entry -> this.resources.get(entry.getKey()) >= entry.getValue());
    }
    
}
