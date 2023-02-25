package it.unibo.controller.impl;

import it.unibo.controller.api.Controller;
import it.unibo.model.api.City;

/**A basic implementation of {@link it.unibo.controller.api.Controller}. */
public class ControllerImpl implements Controller {
    
    private final City city;

    public ControllerImpl(final City city) {
        this.city = city;
    }

    @Override
    public boolean checkResourcesToBuild(String buildingName) {
        return true;
    }

    @Override
    public void removeBuilding(String buildingName) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean upgradeBuilding(String buildingName) {
        return false;
    }
}
