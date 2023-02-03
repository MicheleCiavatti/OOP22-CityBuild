package it.unibo.model.api;
import java.util.Map;

public interface Revenue {
    
    /**
     * Sets the type of Resource to be producted
     * @param r specify the type of resource to be producted
     */
    public void setProduction(Resource resource);

    /**
     * 
     * @return a Map<Resource, Integer> of the resource producted
     */
    public Map<Resource, Integer> getResource();

    /**
     * Starts the production of the material
     */
    public void production();

    /**
     * Sets the time of the production
     * @param time used to specify the amount of time needed for the productions
     * 
     */
    public void setTime(int time);

    
}
