package it.unibo.model.api;

/**Factory class for creating different kinds of buildings.*/
public interface BuildingFactory {
    
    /**Returns an instance of a specific {@link it.unibo.model.api.ProductionBuilding}. 
     * More specifically:
     * WATER -> Depurator;
     * WOOD -> Woodcutter;
     * ENERGY -> Power Plant;
     * METAL -> Foundry;
     * CITIZEN -> House;
     * GOLD -> Mine.
     * @param resource used to specify the building required
     * @return a class implementing {@link it.unibo.model.api.ProductionBuilding} with appropriate functionalities
    */
    public ProductionBuilding createSimpleProductionBuilding(final Resource resource);

    /**Returns an instance of a specific {@link it.unibo.model.api.ProductionBuilding}.
     * More specifically:
     * WATER -> ;
     * WOOD -> ;
     * ENERGY -> ;
     * METAL -> ;
     * CITIZEN -> Skyscraper;
     * GOLD -> ;
     * @param resource used to specify the building required
     * @return a class implementing {@link it.unibo.model.api.ProductionBuilding} with appropriate functionalities
     */
    public ProductionBuilding createAdvancedProductionBuilding(final Resource resource);
}
