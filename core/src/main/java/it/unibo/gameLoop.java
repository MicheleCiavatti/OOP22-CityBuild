package it.unibo;
public class gameLoop {
    private boolean running = true;

    public gameLoop(CityBuild cityBuild, BackgroundTask backgroundTask) {
        this.cityBuild = cityBuild;
        this.backgroundTask = backgroundTask;
    }
    public void start() {
        long lastUpdateTime = System.nanoTime();
        long lastRenderTime = System.nanoTime();
        long now, updateLength, sleepTime, timeSinceLastRender;

        while (running){
            now = System.nanoTime();
            updateLength = now - lastUpdateTime;
            lastUpdateTime = now;
            cityBuild.update(updateLength);
            
            timeSinceLastRender = now - lastRenderTime;
            if (timeSinceLastRender > 1_000_000_000 / 60) {
                lastRenderTime = now;
                cityBuild.render();
            }else{
                sleepTime = (1_000_000_000 - timeSinceLastRender) / 1_000_000;
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
