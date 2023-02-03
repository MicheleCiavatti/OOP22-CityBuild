package it.unibo.model.api;

import java.util.Map;

public interface Cost {
    //sets the cost of the operation
    public void setCost(Map<Resource,Integer> cost);

    //return the value of the cost of the operation
    public Map<Resource, Integer> getCost();

}
