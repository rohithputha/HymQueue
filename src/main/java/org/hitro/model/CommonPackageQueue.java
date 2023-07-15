package org.hitro.model;

import lombok.Getter;
import org.hitro.model.SubscriberMessagePackage;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class CommonPackageQueue {

    private Queue<SubscriberMessagePackage> pQ;

    private CommonPackageQueue(){
        this.pQ = new LinkedList<>();
    }

    public void add(SubscriberMessagePackage subscriberMessagePackage){
        synchronized (this.pQ){
            this.pQ.offer(subscriberMessagePackage);
            this.pQ.notifyAll();
        }
    }

    private static CommonPackageQueue commonPackageQueue;
    public static CommonPackageQueue getInstance(){
        if(commonPackageQueue == null){
            synchronized (CommonPackageQueue.class){
                if(commonPackageQueue==null){
                    commonPackageQueue= new CommonPackageQueue();

                }
            }
        }
        return commonPackageQueue;
    }
}
