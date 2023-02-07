package it.unibo.model.api;

import java.util.Map;
/*This interface is used to Model any type of production cost */
public interface Cost {
    /**
     * sets the cost of the operation
     * @param Map<Resource,Integer> specify the production cost
     */
    public void setCost(Map<Resource,Integer> cost);

    /**
     * Returns the materials and its cost for the operation
     * @return a Map<Resource, Integer> of the cost of the operation
     */ 
    public Map<Resource, Integer> getCost();

}
