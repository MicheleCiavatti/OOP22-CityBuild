package it.unibo.model.impl;

import it.unibo.model.api.Cost;
import it.unibo.model.api.TypeBuilding;

public class ConstructionCostFactory {
    public Cost getType(TypeBuilding type){
        if(type == null){
            return null;
        }
        else if(type == TypeBuilding.UPGRADECONSTRUCTION){
            return new UpgradeCostImpl();
        }
        else if(type == TypeBuilding.FIRSTCONSTRUCTION){
            return new ConstructionCostImpl();
        }
        return null;
    }
}
