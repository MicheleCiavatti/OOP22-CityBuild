package it.unibo.model.api;

/**Enumerates all possible resources of the game. */
public enum Resource {
    WATER("Water", "Depurator", "Ultrafiltration_complex"),
    WOOD("Wood", "Woodcutter", "Lumber_refinary"),
    ENERGY("Energy", "Power_Plant", "Quantum_reactor"),
    METAL("Metal", "Foundry", "Forge"),
    CITIZEN("Citizen", "House", "Skyscraper"),
    GOLD("Gold", "Mine", "Mineral_station");

    private final String name;
    private final String simpleBuilding;
    private final String advancedBuilding;

    private Resource(final String name, final String simpleBuilding, final String advancedBuilding) {
        this.name = name;
        this.simpleBuilding = simpleBuilding;
        this.advancedBuilding = advancedBuilding;
    }

    /**Returns the name of the specific resource.
     * @return a string containing the name of the resource
     */
    public String getName() {
        return this.name;
    }
    
    /**Returns the simple {@link it.unibo.model.impl.ProductionBuildingImpl} that produces the resource.
     * @return a string with the name of the simple building associated, used by {@link it.unibo.controller.impl.EconomyFileReaderImpl} 
     * and {@link it.unibo.model.impl.ProductionBuildingImpl}
     */
    public String getSimpleBuilding() {
        return this.simpleBuilding;
    }

    /**Returns the advanced {@link it.unibo.model.api.ProductionBuildingImpl} that produces the resource.
     * @return a string with the name of the simple building associated, used by {@link it.unibo.controller.impl.EconomyFileReaderImpl}
     * and {@link it.unibo.model.impl.ProductionBuildingImpl}
    */
    public String getAdvancedBuilding() {
        return this.advancedBuilding;
    }
    
}