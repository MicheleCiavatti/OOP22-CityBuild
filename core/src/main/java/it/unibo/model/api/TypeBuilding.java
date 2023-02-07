package it.unibo.model.api;

/*Enumerates of all possible types of typeBuilding */
public enum TypeBuilding {
    FIRST_CONSTRUCTION("FirstConstruction"),
    UPGRADE_CONSTRUCTION("UpgradeConstruction");

    private final String name;

    private TypeBuilding (final String name){
        this.name = name;
    }
    /**Returns the name of the specific 
     * @return a string containing the name of the resource
     */
    public String getName(){
        return this.name;
    }
}
