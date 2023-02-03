package it.unibo.model.api;

/**Factory class for creating different kinds of {@link Building}.*/
public interface BuildingFactory {
    
    /**Returns an instance of a specific {@link ProductionBuilding}. 
     * More specifically:
     * WATER -> Depurator
     * WOOD -> Woodcutter
     * ENERGY -> Power Plant
     * METAL -> Foundry
     * CITIZEN -> House
     * GOLD -> Mine
     * @param resource used to specify the building required
     * @return a class implementing {@link ProductionBuilding}
    */
    public ProductionBuilding createSimpleProductionBuilding(final Resource resource);
}
