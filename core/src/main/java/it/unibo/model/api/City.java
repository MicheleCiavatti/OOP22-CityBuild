package it.unibo.model.api;

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

    /**Returns the resources in possession of the player.
     * @return a map containing all possible values of {@link it.unibo.model.api.Resource} rapresenting the amount of each resource
     * in possesion of the player.
     */
    public Map<Resource, Integer> getPlayerResources();

    /**Returns the number of citizens in the town, NOT the citizens capacity of the town.
     * @return the number of citizens living in the city
     */
    public int getCitizensInTown();

    /**Does a cycle of the game, by updating the resources (based on revenues and citizens requests)
     * and increase/decrease the number of citizens living in the city based on the satisfaction of their requests.
     */
    public void doCycle();
}
