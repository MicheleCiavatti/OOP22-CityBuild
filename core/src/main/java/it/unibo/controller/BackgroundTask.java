package it.unibo.controller;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unibo.controller.impl.BackgroundTaskImpl;

/*A class for executing specific procedures when application is 
closed*/


public class BackgroundTask {
    private ScheduledExecutorService executorService;
    private volatile boolean running = true;

    public BackgroundTask() {
        executorService = Executors.newSingleThreadScheduledExecutor();
    }    

    public void start(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                //deve partire quando l'applicazione viene chiusa
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    BackgroundTaskImpl task = new BackgroundTaskImpl();
                    @Override
                    public void run() {
                        while(running){
                            System.out.println("Running");
                            task.run();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                
                
            }
        };
        executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public void stop(){
        executorService.shutdown();
    }

    public void stopRunning(){
        running = false;
    }

    public void startRunning(){
        running = true;
    }
    
}
