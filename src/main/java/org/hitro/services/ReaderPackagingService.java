package org.hitro.services;

import org.hitro.exceptions.HymQueueException;
import org.hitro.model.CommonPackageQueue;
import org.hitro.model.Message;
import org.hitro.model.Subscriber;
import org.hitro.model.SubscriberMessagePackage;

import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

public class ReaderPackagingService implements Runnable{

    private volatile Set<Subscriber> subscriberSet;
    private volatile Queue<Message> chQ;

    private volatile CommonPackageQueue commonPackageQueue;
    public ReaderPackagingService(Set<Subscriber> subscriberSet, Queue<Message> chQ){
        this.subscriberSet = subscriberSet;
        this.chQ =  chQ;
        this.commonPackageQueue = CommonPackageQueue.getInstance();
    }
    @Override
    public void run() {
        synchronized(chQ){
            do{
//                Iterator setItertator = subscriberSet.iterator();
                while(chQ.size()==0){
                    try {
                        chQ.wait();
                    } catch (InterruptedException e) {
                        throw new HymQueueException(e);
                    }
                }
                Message newMessage = chQ.poll();
                for(Subscriber subscriber: subscriberSet){
                        SubscriberMessagePackage subscriberMessagePackage = new SubscriberMessagePackage(subscriber,newMessage);
                        this.commonPackageQueue.add(subscriberMessagePackage);
                    }
            }while(true);

        }
    }

    public void wakeThread(){
        synchronized (chQ){
            chQ.notify();
        }
    }
}
