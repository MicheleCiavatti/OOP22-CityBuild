package it.unibo.controller;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unibo.controller.impl.BackgroundTaskImpl;

/*A class that provides a background task that runs while the application is not open.*/
public class BackgroundTask {
    private ScheduledExecutorService executorService;
    private volatile boolean running = true;
    
    /* Constructs a new BackgroundTask instance with a single thread scheduled executor service. */
    public BackgroundTask() {
        executorService = Executors.newSingleThreadScheduledExecutor();
    }    

    /* Starts the background task*/
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

    /* Stops the background task */
    public void stop(){
        executorService.shutdown();
    }
    /* Stops the running state of the background task */
    public void stopRunning(){
        running = false;
    }
    /* tarts the running state of the background task */
    public void startRunning(){
        running = true;
    }
    
}
