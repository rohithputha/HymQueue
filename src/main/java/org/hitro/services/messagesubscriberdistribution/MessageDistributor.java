package org.hitro.services.messagesubscriberdistribution;

import org.hitro.model.subscribers.SubscriberMessagePackage;

import java.util.Queue;

public class MessageDistributor implements Runnable{

    private Queue<SubscriberMessagePackage> packageQueue;

    public MessageDistributor(Queue<SubscriberMessagePackage> packageQueue){
        this.packageQueue = packageQueue;
    }
    @Override
    public void run() {
        synchronized (packageQueue){
            do{
                while(this.packageQueue.size()==0){
                    try {
                        packageQueue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                SubscriberMessagePackage subscriberMessagePackage = packageQueue.poll();
                subscriberMessagePackage.getSubscriber().getCallbackFunction().consume(subscriberMessagePackage.getMessage().getData());

            }while(true);
        }

    }
}
