package it.unibo.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.model.api.Resource;
import it.unibo.model.api.Revenue;

import java.util.Timer;
import java.util.TimerTask;


public class RevenueImpl implements Revenue{

    Resource materialProducted;
    private int time=30000;
    private Map<Resource, Integer> resourceMap = new HashMap<>();


    @Override
    public void setProduction(Resource r) {
        this.materialProducted = r;
        setResource(materialProducted);
    }

    private void setResource(Resource r){
        if(!resourceMap.containsKey(r)){
            resourceMap.put(r, 0);
        }
    }

    @Override
    public Map<Resource, Integer> getResource() {
        return resourceMap;
    }

    //Da sistemare
    @Override
    public void production() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                
                resourceMap.computeIfPresent(materialProducted, (key, val) -> val + 1);
                System.out.println("Produzione di " + resourceMap.keySet() + " eseguito.");
            }
        };
        
        timer.schedule(timerTask, time);
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }
    
}   
