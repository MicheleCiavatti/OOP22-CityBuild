package it.unibo.model.api;
import java.util.Map;

/**This interface is used to model all the necessary resources of the buiding.
 * It gives all the cost and the revenue of the two type of building, simple building and advanced building.
 */
public interface EconomyHandler {

    /**
     * This method is used to return a map of the simple building's construction cost
     * @param resource is used to define which type of building among the simple building it is
     * @return a Map<Resource,Integer> of the building's first construction cost
     */
    public Map<Resource,Integer> getSimpleCostTable(final Resource resource);
    
    /**
     * This method is used to return a map of the simple building's revenue
     * @param resource is used to define which type of building among the simple building it is
     * @return a Map<Resource,Integer> of the building's revenue
     */
    public Map<Resource,Integer> getSimpleRevenueTable(final Resource resource);

    /**
     * This method is used to return a map of the simple building's upgrade cost.
     * @param resource is used to define which type of building among the simple building it is
     * @return a Map<Resource,Integer> of the building's upgrade cost
     */
    public Map<Resource,Integer> getSimpleUpgradeTable(final Resource resource);

    /**
     * This method is used to return a map of the advanced building's construction cost
     * @param resource is used to define which type of building among the advanced building it is
     * @return a Map<Resource,Integer> of the building's first construction cost
     */
    public Map<Resource, Integer> getAdvancedCostTable(final Resource resource);

    /**
     * This method is used to return a map of the advanced building's revenue
     * @param resource is used to define which type of building among the advanced building it is
     * @return a Map<Resource,Integer> of the building's revenue
     */
    public Map<Resource, Integer> getAdvancedRevenueTable(final Resource resource);

    /**
     * This method is used to return a map of the advanced buildings
     * @param resource is used to define which type of building among the advanced building it is
     * @return a Map<Resource,Integer> of the building's upgrade cost
     */
    public Map<Resource, Integer> getAdvancedUpgradeTable(final Resource resource);

}
