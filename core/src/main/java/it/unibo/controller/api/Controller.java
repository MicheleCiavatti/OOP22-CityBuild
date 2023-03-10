package it.unibo.controller.api;

public interface Controller {
    public boolean checkResourcesAndBuild(final String buildingName);

    public void removeBuilding(final String buildingName);

    public boolean upgradeBuilding(final String buildingName);

    public int getCitizensInTown();

    
}
