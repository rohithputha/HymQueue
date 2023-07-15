package org.hitro.services.messagesubscriberdistribution;

import org.hitro.model.CommonPackageQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
