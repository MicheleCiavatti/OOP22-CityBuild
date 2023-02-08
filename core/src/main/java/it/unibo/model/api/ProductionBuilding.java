package it.unibo.model.api;

import java.util.Map;

/**This interface is used to Model any building that produces resources */
public interface ProductionBuilding {

    /** */
    public Map<Resource, Integer> getRevenue();

    /** */
    public Map<Resource, Integer> getCostUpgrade();

    /** */
    public Map<Resource, Integer> getCostConstruction();
    
    /** */
    public void upgrade(final Map<Resource, Integer> resourcesForUpgrade);

}