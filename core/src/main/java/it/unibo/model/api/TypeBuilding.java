package it.unibo.model.api;


public enum TypeBuilding {
    FIRSTCONSTRUCTION("FirstConstruction"),
    UPGRADECONSTRUCTION("UpgradeConstruction");

    private final String name;

    private TypeBuilding (final String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
