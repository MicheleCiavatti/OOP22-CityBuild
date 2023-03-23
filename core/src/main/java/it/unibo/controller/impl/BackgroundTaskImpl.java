package it.unibo.controller.impl;


import java.util.Map;

import it.unibo.controller.api.Controller;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.PlayerImpl;

public class BackgroundTaskImpl {
    //mappa che contiene le risorse da incrementare
    
    public Map<Resource, Integer> resources;


    public void setResources() {
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
    


}
