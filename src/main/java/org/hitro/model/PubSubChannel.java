package org.hitro.model;

import lombok.Getter;
import org.hitro.model.interfaces.Channel;
import org.hitro.model.metadatas.ChannelMetadata;
import org.hitro.model.metadatas.interfaces.ChannelMetadataIf;
import org.hitro.model.metadatas.interfaces.ChannelType;
import org.hitro.services.HashService;
import org.hitro.services.HymQueue;
import org.hitro.services.ReaderPackagingService;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PubSubChannel implements Channel {
    private volatile Queue<Message> chQ;

    private volatile Set<Subscriber> subscriberSet;

    private volatile Queue<SubscriberMessagePackage> packageQueue;
    private HashService hashService;
    private ReaderPackagingService packagingService;
    @Getter
    private ChannelMetadataIf metadata;
    public PubSubChannel(String name){
        try {
            hashService =  new HashService();
            this.chQ = new LinkedList<>();
            this.subscriberSet = new HashSet<>();
            this.packageQueue = new LinkedList<>();
            this.metadata = new ChannelMetadata(hashService.getHashVal(name),name, ChannelType.PUBSUB);
            this.packagingService = new ReaderPackagingService(subscriberSet,chQ);
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
    public Message next(){
        return null;
    }
}
