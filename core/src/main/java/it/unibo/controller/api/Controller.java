package it.unibo.controller.api;

import java.util.Map;

import it.unibo.model.api.Resource;

public interface Controller {

    public boolean checkResourcesAndBuild(final String buildingName);

    public void removeBuilding(final String buildingName);

    public boolean upgradeBuilding(final String buildingName);

    public int getCitizensInTown();

    public Map<Resource, Integer> getCost(final String buildingName);

    public Map<Resource, Integer> getUpgrade(final String buildingName);

    public Map<Resource, Integer> getRevenue(final String buildingName);

    public Map<Resource, Integer> getPlayerResources();

    public void doCycle(); 

    /**A method to add a lot of resources to the player. */
    public void cheatCode();
}
