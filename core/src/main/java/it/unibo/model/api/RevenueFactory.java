package it.unibo.model.api;
import java.util.Map;

public interface RevenueFactory {
    
    //Imposta la produzione dell'edificio
    public void setProduction(Resource r);

    //Ritorna la revenue, il materiale prodotto
    public Map<Resource, Integer> getResource();

    //Fa partire la produzione
    public void production();

    //Imposta il tempo di produzione
    public void setTime(int time);

    
}
