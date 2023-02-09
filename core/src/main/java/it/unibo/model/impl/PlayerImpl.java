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

    /**{@inheritDoc} */
    @Override
    public int getResource(final Resource r) {
        return this.resources.get(r);
    }

    /**{@inheritDoc} */
    @Override
    public Map<Resource, Integer> getAllResources() {
        return Map.copyOf(this.resources);
    }

    /**{@inheritDoc} */
    @Override
    public boolean spendResources(Map<Resource, Integer> toSpend) {
        var input = this.transform(toSpend, false);
        if (this.checkResourcesToSpend(input)) {
            this.setResources(input);
            return true;
        }
        return false;
    }

    /**{@inheritDoc} */
    @Override
    public void addResources(final Map<Resource, Integer> toAdd) {
        var input = this.transform(toAdd, true);
        this.setResources(input);
    }

    //A method that transforms all values of a map as positive if alwaysPositive == true, negative if alwaysPositive == false
    private Map<Resource, Integer> transform(final Map<Resource, Integer> origin, final boolean alwaysPositive) {
        Map<Resource, Integer> out = new HashMap<>();
        origin.entrySet().forEach(entry -> out.put(entry.getKey(), Math.abs(entry.getValue()) * (alwaysPositive ? 1 : -1)));
        return out;
    }
    
    private void setResources(final Map<Resource, Integer> map) {
        map.entrySet()
            .forEach(entry -> this.resources.replace(entry.getKey(), this.resources.get(entry.getKey()) + entry.getValue()));
    }

    private boolean checkResourcesToSpend(Map<Resource, Integer> toSpend) {
        return toSpend.entrySet()
            .stream()
            .allMatch(entry -> this.resources.get(entry.getKey()) + entry.getValue() >= 0);
    }
}
