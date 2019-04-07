package main.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceWrapper {
    private ExecutorService executorService;

    public ExecutorServiceWrapper() {
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void execute(Runnable r) {
        this.executorService.execute(r);
    }

    public Future sumbit(Runnable r) {
        return this.executorService.submit(r);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
