package it.unibo.model.api;

public enum Resource {
    WATER("Water"),
    WOOD("Wood"),
    ENERGY("Energy"),
    METAL("Metal"),
    CITIZEN("Citizen");


    private final String name;

    private Resource(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
}