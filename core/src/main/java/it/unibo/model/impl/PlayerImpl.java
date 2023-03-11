package it.unibo.model.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;

/**An implementation of {@link it.unibo.model.api.Player}. */
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
        Map<Resource, Integer> input = new HashMap<>(toSpend);
        input.replaceAll(this.func(false));
        if (this.checkResourcesToSpend(input)) {
            this.setResources(input, false);
            return true;
        }
        return false;
    }

    /**{@inheritDoc} */
    @Override
    public void addResources(final Map<Resource, Integer> toAdd) {
        Map<Resource, Integer> input = new HashMap<>(toAdd);
        input.replaceAll(this.func(true));
        this.setResources(input, true);
    }

    private void setResources(final Map<Resource, Integer> map, final boolean addCitizens) {
        map.entrySet().stream().filter(entry -> addCitizens ? true : entry.getKey() != Resource.CITIZEN)
            .forEach(entry -> this.resources.replace(entry.getKey(), this.resources.get(entry.getKey()) + entry.getValue()));
    }

    private boolean checkResourcesToSpend(Map<Resource, Integer> toSpend) {
        return toSpend.entrySet()
            .stream()
            .allMatch(entry -> this.resources.get(entry.getKey()) + entry.getValue() >= 0);
    }

    /*A BiFunction that can be used by Map.replaceAll method. If alwaysPositive == true, then
     * the function transform an integer of the map into positve, negative otherwise.
     */
    private BiFunction<Resource, Integer, Integer> func(final boolean alwaysPositive) {
        return (k, v) -> Math.abs(v) * (alwaysPositive ? 1 : -1);
    }
}
