package it.unibo.model.impl;

import it.unibo.model.api.Cost;
import it.unibo.model.api.TypeBuilding;

public class ConstructionCostFactory {
    public Cost getType(TypeBuilding type){
        if(type == null){
            return null;
        }
        else if(type == TypeBuilding.UPGRADE_CONSTRUCTION){
            return new UpgradeCostImpl();
        }
        else if(type == TypeBuilding.FIRST_CONSTRUCTION){
            return new ConstructionCostImpl();
        }
        return null;
    }
}
