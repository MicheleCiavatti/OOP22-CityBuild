package it.unibo;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*A class for executing specific procedures when application is 
closed*/


public class BackgroundTask {
    private ScheduledExecutorService executorService;

    public BackgroundTask() {
        executorService = Executors.newSingleThreadScheduledExecutor();
    }    

    public void start(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        };
        executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public void stop(){
        executorService.shutdown();
    }
    
}
