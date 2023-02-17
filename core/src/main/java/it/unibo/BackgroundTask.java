package it.unibo;

/*A class for executing specific procedures when application is 
closed*/


public class BackgroundTask {
    private ScheduledExecutorService executorService;

    public BackgroundTask() {
        executorService = Executors.newSingleThreadExecutor();
    }    

    public void start(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        };
        }
    }
    
}
