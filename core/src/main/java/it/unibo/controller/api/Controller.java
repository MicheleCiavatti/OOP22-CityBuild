package it.unibo.controller.api;

import java.util.Map;

import it.unibo.model.api.Resource;

/**This interface models the controller for the application.
 * In the MVC, this is the Controller.
 */
public interface Controller {
    
    public boolean checkResourcesAndBuild(final String buildingName);

    public void removeBuilding(final String buildingName);

    public boolean upgradeBuilding(final String buildingName);

    public Map<Resource, Integer> getCostForBuilding(final String buildingName);

    public Map<Resource, Integer> getPlayerResources();

    public int getCitizensInTown();

    public void doCycle();
}
