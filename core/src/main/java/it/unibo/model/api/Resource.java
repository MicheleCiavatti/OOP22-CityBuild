package it.unibo.model.api;

/**Enumerates all possible resources of the game. */
public enum Resource {
    WATER("Water", "Purificator"),
    WOOD("Wood", "Woodcutter"),
    ENERGY("Energy", "Power Plant"),
    METAL("Metal", "Foundry"),
    CITIZEN("Citizen", "House"),
    GOLD("Gold", "Mine");

    private final String name;
    private final String simpleBuilding;

    private Resource(final String name, final String simpleBuilding) {
        this.name = name;
        this.simpleBuilding = simpleBuilding;
    }

    /**Returns the name of the specific resource.
     * @return a string containing the name of the resource
     */
    public String getName() {
        return this.name;
    }
    
    /**Returns the {@link it.unibo.model.impl.SimpleProductionBuilding} that produces the resource.
     * @return a string with the name of the simple building associated, used by {@link it.unibo.controller.impl.EconomyFileReaderImpl}
     */
    public String getSimpleBuilding() {
        return this.simpleBuilding;
    }
    
}