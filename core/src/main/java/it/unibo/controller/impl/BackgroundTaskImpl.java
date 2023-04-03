package it.unibo.controller.impl;

import java.util.Map;
import java.util.Random;

import it.unibo.controller.BackgroundTask;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.PlayerImpl;

/* A class that implements a background task that increases the player's resources with randomized values */
public class BackgroundTaskImpl {

    private Map<Resource, Integer> resources;
    private Random random = new Random();
    private int goldValue;
    private int genericValue;
    Player player = new PlayerImpl();
    BackgroundTask task = new BackgroundTask();

    private static final int MAX_GOLD_VALUE = 100;
    private static final int MAX_GENERIC_VALUE = 10;

    private void setResources() {
        final Player player = new PlayerImpl();
        resources = player.getAllResources();
    }

    /**
     * Returns the resources map with the player's resources.
     * If the resources map is null, it is set using setResources().
     * 
     * @return The resources map with the player's resources
     */
    public Map<Resource, Integer> getResources() {
        if (resources == null) {
            setResources();
        }
        return resources;
    }

    /**
     * Increases the player's resource with the provided value.
     * 
     * @param res The resource to increase.
     * 
     * @param value The value to increase the resource with.
     */
    public void increaseValue(Resource res, int value) {
        if (res == Resource.GOLD) {
            player.addResources(Map.of(Resource.GOLD, goldValue));
        } else {
            player.addResources(Map.of(res, genericValue));
        }

    }

    private void computeValue() {
        int randomizedGoldValue = random.nextInt(MAX_GOLD_VALUE);
        int randomizedGenericValue = random.nextInt(MAX_GENERIC_VALUE);
        goldValue = randomizedGoldValue;
        genericValue = randomizedGenericValue;
    }

    /**
     * Runs the background task by computing the randomized values and increasing
     * the player's resources with them.
     * It also stops the running state of the task and stops the task itself.
     */
    public void run() {
        System.out.println("BackgroundTask started");
        computeValue();
        getResources().forEach(this::increaseValue);
        task.stopRunning();
        task.stop();

    }

}
