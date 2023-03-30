package it.unibo.controller.impl;

import java.util.Map;
import java.util.Random;

import it.unibo.controller.BackgroundTask;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.PlayerImpl;

public class BackgroundTaskImpl {

    private Map<Resource, Integer> resources;
    private Random random = new Random();
    private int goldValue;
    private int genericValue;
    BackgroundTask task = new BackgroundTask();

    private static final int MAX_GOLD_VALUE = 100;
    private static final int MAX_GENERIC_VALUE = 10;

    private void setResources() {
        final Player player = new PlayerImpl();
        // ottiene le risorse con le rispettive quantità
        resources = player.getAllResources();
    }

    public Map<Resource, Integer> getResources() {
        if (resources == null) {
            setResources();
        }
        return resources;
    }

    public void increaseValue(Resource res, int value) {
        if (res == Resource.GOLD) {
            resources.put(res, resources.get(res) + goldValue);
        } else {
            resources.put(res, resources.get(res) + genericValue);
        }

    }

    private void computeValue() {
        
        int randomizedGoldValue = random.nextInt(MAX_GOLD_VALUE);
        int randomizedGenericValue = random.nextInt(MAX_GENERIC_VALUE);
    
        goldValue = randomizedGoldValue;
        genericValue = randomizedGenericValue;
    }

    

    public void run() {
        System.out.println("BackgroundTask started");
        computeValue();
        getResources().forEach(this::increaseValue);
        
        task.stopRunning();
        task.stop();

    }

}
