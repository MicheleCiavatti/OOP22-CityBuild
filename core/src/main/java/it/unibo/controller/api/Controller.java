package it.unibo.controller.api;

import java.util.Map;

import it.unibo.model.api.Resource;

public interface Controller {

    /**
     * Checks the resources and builds a building with the given name.
     * 
     * @param buildingName the name of the building to build
     * @return true if the building was successfully built, false otherwise
     */
    public boolean checkResourcesAndBuild(final String buildingName);

    /**
     * Remove the specified building from the city.
     * 
     * @param buildingName the name of the building to remove
     */
    public void removeBuilding(final String buildingName);

    /**
     * Upgrade the specified building in the city.
     * 
     * @param buildingName the name of the building to upgrade
     * @return true if the upgrade was successful, false otherwise
     */ 
    public boolean upgradeBuilding(final String buildingName);

    /**
     * Gets the number of citizens in the city.
     * 
     * @return the number of citizens in the city
     */
    public int getCitizensInTown();

    /**
     * Returns the cost of building a building with the given name in the form of a map of resources and their required quantities.
     * 
     * @param buildingName the name of the building to get the cost for
     * @return a map of resources and their required quantities for building the specified building
     */
    public Map<Resource, Integer> getCost(final String buildingName);

    /**
     * Returns the upgrade cost of a building with the given name in the form of a map of resources and their required quantities.
     * 
     * @param buildingName the name of the building to get the upgrade cost for
     * @return a map of resources and their required quantities for upgrading the specified building
    */
    public Map<Resource, Integer> getUpgrade(final String buildingName);

    /**
     * Returns the revenue of a building with the given name in the form of a map of resources and their quantities produced.
     * 
     * @param buildingName the name of the building to get the revenue for
     * @return a map of resources and their quantities produced by the specified building
     */
    public Map<Resource, Integer> getRevenue(final String buildingName);

    /**
     * Returns the player's current resources in the form of a map of resources and their quantities.
     * 
     * @return a map of resources and their quantities that represent the player's current resources
    */
    public Map<Resource, Integer> getPlayerResources();

    /**
     * Performs a game cycle by updating the city and checking for the occurrence of a fire.
    */
    public void doCycle(); 

    /**A method to add a lot of resources to the player. */
    public void cheatCode();
}
