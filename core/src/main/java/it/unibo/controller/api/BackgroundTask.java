package it.unibo.controller.api;

import java.util.Map;

import it.unibo.model.api.Resource;

public interface BackgroundTask {
    public Map<Resource, Integer> getResources();

    public void increaseValue(Resource res, int value);

    public void run();
}
