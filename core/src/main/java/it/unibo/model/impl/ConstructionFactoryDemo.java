package it.unibo.model.impl;

import java.util.HashMap;

import it.unibo.model.api.Resource;
import it.unibo.model.api.TypeBuilding;
import it.unibo.model.api.Cost;
import java.util.Map;
/**
 * Class test ConstructionFactoryDemo in order to test ConstructionCostFactory Class
 */
public class ConstructionFactoryDemo {
    public static void main(String[] args) {
        ConstructionCostFactory constructionFactory = new ConstructionCostFactory();
        Cost construction1 = constructionFactory.getType(TypeBuilding.FIRST_CONSTRUCTION);

        Map<Resource, Integer> costConstructionMap1 = new HashMap<>();
        costConstructionMap1.put(Resource.WATER, 1);
        construction1.setCost(costConstructionMap1);
        System.out.println(construction1.getCost());

    }
}
