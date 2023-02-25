package it.unibo.controller.api;

/**This interface models the controller for the application.
 * In the MVC, this is the Controller.
 */
public interface Controller {
    
    public boolean checkResourcesToBuild(final String buildingName);

    public void removeBuilding(final String buildingName);

    public boolean upgradeBuilding(final String buildingName);

    public int getCitizensInTown();

    public void doCycle();
}
