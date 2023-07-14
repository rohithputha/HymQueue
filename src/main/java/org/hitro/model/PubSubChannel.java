package org.hitro.model;

import org.hitro.model.metadatas.ChannelMetadata;
import org.hitro.model.metadatas.interfaces.ChannelMetadataIf;
import org.hitro.model.metadatas.interfaces.ChannelType;
import org.hitro.services.HashService;
import org.hitro.services.ReaderPackagingService;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PubSubChannel {
    private volatile Queue<Message> chQ;
    private ChannelMetadataIf channelMetadata;

    private volatile Set<Subscriber> subscriberSet;

    private volatile Queue<SubscriberMessagePackage> packageQueue;
    private HashService hashService;
    private ReaderPackagingService packagingService;
    public PubSubChannel(String name){
        try {
            hashService =  new HashService();
            this.chQ = new LinkedList<>();
            this.channelMetadata = new ChannelMetadata(hashService.getHashVal(name),name, ChannelType.PUBSUB);
            this.packagingService = new ReaderPackagingService(subscriberSet,chQ,packageQueue);
            Thread packagingThread = new Thread(packagingService);
            packagingThread.start();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean add(Message message){
        this.chQ.offer(message);
        this.packagingService.wakeThread();
        return true;
    }

    public boolean addSubscriber(Subscriber subscriber){
        this.subscriberSet.add(subscriber);
        return true;
    }

    public SubscriberMessagePackage get(){
        return this.packageQueue.poll();
    }

}
