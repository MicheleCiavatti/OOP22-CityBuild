package it.unibo.model.impl;

import java.util.Map;
import it.unibo.model.api.Building;
import it.unibo.model.api.Resource;

public class BuildingImpl implements Building {

    private int resource;
    private final Map<Resource, Integer> resourcesRequiredUpgrade = null; //TODO

    public BuildingImpl(final Resource r) {
        //TODO: switch for setting this.resource
        this.resource = 0;
    }

    @Override
    public int getResource() {
        return this.resource;
    }

    @Override
    public Map<Resource, Integer> getCostUpgrade() {
        // TODO: create map for setting cost upgrade
        return null;
    }

    @Override
    public void upgrade(Map<Resource, Integer> resourcesForUpgrade) {
        //TODO: check if the argument is valid for upgrading
        this.resource = 0; //TODO: upgrade the resource
    }
    
}
