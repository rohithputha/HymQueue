package org.hitro;

import org.hitro.model.HymOutput;
import org.hitro.model.metadatas.interfaces.ChannelType;
import org.hitro.services.HymQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HymQueue hymQueue = new HymQueue();
        hymQueue.createChannel("testChannel", ChannelType.POLL);
        hymQueue.add("hello","testChannel","123");
        hymQueue.add(123,"testChannel","123");
        System.out.println(hymQueue.get("testChannel"));
        System.out.println(hymQueue.get("testChannel"));
        System.out.println(hymQueue.get("hello"));
        System.out.println("--------");
        hymQueue.createChannel("testChannel2",ChannelType.PUBSUB);
        hymQueue.addSubscriber("testChannel2", (HymOutput hymOutput)->System.out.println(hymQueue));
        hymQueue.add("sub data","testChannel2","123");
        Thread.sleep(1000);
        System.out.println(hymQueue.getSubscriberMessagePackage("testChannel2"));
    }
}