package it.unibo.model.api;

/**Enumerates all possible resources of the game */
public enum Resource {
    WATER("Water"),
    WOOD("Wood"),
    ENERGY("Energy"),
    METAL("Metal"),
    CITIZEN("Citizen"),
    GOLD("Gold");

    private final String name;

    private Resource(final String name) {
        this.name = name;
    }

    /**Returns the name of the specific resource
     * @return a string containing the name of the resource
     */
    public String getName() {
        return this.name;
    }
    
}