package it.unibo.model.api;

public enum Resource {
    WATER("Water", "Purificator"),
    WOOD("Wood", "Woodcutter"),
    ENERGY("Energy", "Power Plant"),
    METAL("Metal", "Foundry"),
    CITIZEN("Citizen", "House");

    private final String name;
    private final String simpleBuilding;

    private Resource(final String name, final String simpleBuilding) {
        this.name = name;
        this.simpleBuilding = simpleBuilding;
    }

    public String getName() {
        return this.name;
    }
    
    public String getSimpleBuilding() {
        return this.simpleBuilding;
    }
    
}