package it.unibo.model.api;

import java.util.Map;

/**This interface is used to model any building that produces resources.
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

    public String getName();
    
    /**Upgrades the building. 
     * @param resourcesForUpgrade the method makes a check to verify that the resources passed are appropriate 
     * for upgrading the building
     */
    public void upgrade(final Map<Resource, Integer> resourcesForUpgrade);

}