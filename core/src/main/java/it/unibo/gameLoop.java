package it.unibo;

import it.unibo.controller.BackgroundTask;

public class gameLoop {
    private boolean running = true;
    private CityBuild cityBuild;
    private BackgroundTask backgroundTask;
    private final int TARGET_FPS = 60;
    private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;


    public gameLoop(CityBuild cityBuild, BackgroundTask backgroundTask) {
        this.cityBuild = cityBuild;
        this.backgroundTask = backgroundTask;
    }
    public void start() {
        //cityBuild.start(); //avvia il gioco //TODO
        long lastUpdateTime = System.nanoTime();
        long lastRenderTime = System.nanoTime();
        long now, updateLength, sleepTime, timeSinceLastRender;

        while (running){
            now = System.nanoTime();
            updateLength = now - lastUpdateTime;
            lastUpdateTime = now;
            //cityBuild.update(updateLength);   TODO
            
            timeSinceLastRender = now - lastRenderTime;
            if (timeSinceLastRender > OPTIMAL_TIME) {
                lastRenderTime = now;
                cityBuild.render();
            }else{
                sleepTime = (OPTIMAL_TIME - timeSinceLastRender) / 1_000_000;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }

        backgroundTask.start();

    }

    public void stop() {
        running = false;
    }
}
