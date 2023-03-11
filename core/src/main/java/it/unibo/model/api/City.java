package it.unibo.model.api;

import java.util.List;
import java.util.Map;

/**This interface models the city of the {@link it.unibo.model.api.Player}.
 * In the MVC, this represents the Model.*/
public interface City {
    
    /**Attempts to build the specified building.
     * @param building the building that needs to be constructed
     * @return true if the building was succesfully constructed, false otherwise
    */
    public boolean build(final ProductionBuilding building);

    /**Removes the building specified. If multiple buildings of the same type are present, it should remove first the
     * ones not upgraded.
     * @param building the building that needs to be demolished: if no building of the same kind is present, the method does nothing.
     */
    public void demolish(final ProductionBuilding building);

    /**Upgrades the building specified, if upgradable and if the player has enough resources. The method is safe towards requests
     * of upgrading a building not present in the city.
     * @param building the building that needs to be upgraded: if multiple of the same type, the first not upgraded is considered
     * @return true if the building was succesfully upgraded, false otherwise
     */
    public boolean upgrade(final ProductionBuilding building);

    /**Returns the resources in possession of the player.
     * @return a map containing all possible values of {@link it.unibo.model.api.Resource} rapresenting the amount of each resource
     * in possesion of the player. The value of CITIZEN in this map represents the city's capacity towards people, not the current 
     * citizens living in the city.
     */
    public Map<Resource, Integer> getPlayerResources();

    /**Returns the number of citizens in the town, NOT the citizens capacity of the town.
     * @return the number of citizens living in the city
     */
    public int getCitizens();

    /**Returns a list containing the {@link it.unibo.model.api.ProductionBuilding} built in the city.
     * @return a list with the buildings present in the city
     */
    public List<ProductionBuilding> getBuildings();

    /**Does a cycle of the game, by updating the resources (based on revenues and citizens requests)
     * and increase/decrease the number of citizens living in the city based on the satisfaction of their requests.
     */
    public void doCycle();
}
