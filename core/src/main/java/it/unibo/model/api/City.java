package it.unibo.model.api;

import java.util.Map;

/**This interface models the city of the player.
 * In the MVC, this is the Model.
 */
public interface City {
    
    /**Attempts to build the specified building.
     * @param building the building that needs to be constructed
     * @return true if the building was succesfully constructed, false otherwise
    */
    public boolean attemptBuild(final ProductionBuilding building);

    /**Removes the building specified. If multiple buildings of the same type are present, it should remove first the
     * ones not upgraded.
     * @param building the building that needs to be demolishes
     */
    public void removeBuilding(final ProductionBuilding building);

    /**Upgrades the building specified, if upgradable and if the player has enough resources.
     * @param building the building that needs to be upgraded: if multiple of the same type, the first not upgraded is considered
     * @return true if the building was succesfully upgraded, false otherwise
     */
    public boolean upgradeBuilding(final ProductionBuilding building);
}
