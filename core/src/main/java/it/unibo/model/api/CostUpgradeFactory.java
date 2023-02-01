package it.unibo.model.api;
import java.util.Map;

public interface CostUpgradeFactory {
    //Imposta il costo di upgrade dell'edificio
    public void setCostUpgrade(Map<Resource, Integer> costUpgrade);
    
    //Ritorna il costo di upgrade dell'edificio
    public Map<Resource, Integer> getCostUpgrade();
}
