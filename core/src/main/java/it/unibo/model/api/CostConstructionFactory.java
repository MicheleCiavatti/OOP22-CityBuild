package it.unibo.model.api;
import java.util.Map;

public interface CostConstructionFactory {
    //Imposta il costo di costruzione dell'edificio
    public void setCostConstruction(Map<Resource, Integer> costConstruction);

    //Ritorna il costo di costruzione dell'edificio
    public Map<Resource, Integer> getCostConstruction();
}
