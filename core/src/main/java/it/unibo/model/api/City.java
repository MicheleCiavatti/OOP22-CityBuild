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
}
