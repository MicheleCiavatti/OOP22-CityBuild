package it.unibo.model.api;
import java.util.Map;

public interface Revenue {
    
    //Sets the type of material to produce
    public void setProduction(Resource r);

    //Returns the amount of material producted
    public Map<Resource, Integer> getResource();

    //Starts the production of the material
    public void production();

    //Sets the amount of time for the production
    public void setTime(int time);

    
}
