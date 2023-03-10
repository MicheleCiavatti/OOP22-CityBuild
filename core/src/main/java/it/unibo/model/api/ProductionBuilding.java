package it.unibo.model.api;

import java.util.Map;

/**This interface is used to model any building that produces resources.
 * It's also used for buildings that allow for more citizen capacity in the city, with some slight changes
 * @see it.unibo.model.api.Resource
*/
public interface ProductionBuilding {

    /**Returns the revenue of the building.
     * @return a {@link java.util.Map} representing the revenue of the production building
     */
    public Map<Resource, Integer> getRevenue();

    /**Returns the upgrade cost of the building.
     * @return a {@link java.util.Map} representing the upgrade cost of the production building
     */
    public Map<Resource, Integer> getCostUpgrade();

    /**Returns the construction cost of the building.
     * @return a {@link java.util.Map} representing the construction cost of the production building
     */
    public Map<Resource, Integer> getCostConstruction();

    /**Returns the name of the production building.
     * @return a string containing the name of the production building. The string is obtained through 
     * {@link it.unibo.model.api.Resource} methods.
     */
    public String getName();
    
    /**Upgrades the building. 
     * @param resourcesForUpgrade the method makes a check to verify that the resources passed are appropriate 
     * for upgrading the building
     */
    public boolean upgrade(final Map<Resource, Integer> resourcesForUpgrade);

    /**Whatever the building has not been already upgraded.
     * @return true if the building has not been upgraded yet, false otherwise
     */
    public boolean isUpgradable();

}