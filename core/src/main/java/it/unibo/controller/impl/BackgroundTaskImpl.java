package it.unibo.controller.impl;


import java.util.Map;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.PlayerImpl;

public class BackgroundTaskImpl {
    
    private Map<Resource, Integer> resources;


    private void setResources() {
        final Player player = new PlayerImpl();
        //ottiene le risorse con le rispettive quantità
        resources = player.getAllResources();
    }

    public Map<Resource, Integer> getResources() {
        if (resources == null) {
            setResources();
        }
        return resources;
    }

    public void increaseValue(Resource res, int value) {
        resources.put(res, resources.get(res) + value);
    }
    
    private int computeValue(){
        return 0; //TODO
    }



    


}
