package org.hitro.services;

import org.hitro.model.CommonPackageQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class MessageDistributorService {
    private static final int threadExecutors = 3;
    public static void assignExecutorTasks(){

        CommonPackageQueue commonPackageQueue = CommonPackageQueue.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(threadExecutors);
        for (int i=0;i<threadExecutors;i++){
            MessageDistributor messageDistributor = new MessageDistributor(commonPackageQueue.getPQ());
            executorService.execute(messageDistributor);
        }
    }


}
