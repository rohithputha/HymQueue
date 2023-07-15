package org.hitro.model.channels;

import lombok.Getter;
import org.hitro.model.iodtos.Message;
import org.hitro.model.subscribers.Subscriber;
import org.hitro.model.subscribers.SubscriberMessagePackage;
import org.hitro.model.metadatas.ChannelMetadata;
import org.hitro.model.metadatas.interfaces.ChannelMetadataIf;
import org.hitro.publicinterfaces.ChannelType;
import org.hitro.services.HashService;
import org.hitro.services.ReaderPackagingService;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PubSubChannel implements Channel {
    private volatile Queue<Message> chQ;

    private volatile Set<Subscriber> subscriberSet;

    private volatile Queue<SubscriberMessagePackage> packageQueue;
    private ReaderPackagingService packagingService;
    @Getter
    private ChannelMetadataIf metadata;
    private  PubSubChannel(ChannelMetadataIf channelMetadata){
            this.chQ = new LinkedList<>();
            this.subscriberSet = new HashSet<>();
            this.packageQueue = new LinkedList<>();
            this.metadata = channelMetadata;
            this.packagingService = new ReaderPackagingService(subscriberSet,chQ);
            Thread packagingThread = new Thread(packagingService);
            packagingThread.start();
    }
    public boolean add(Message message){
        synchronized (this.chQ){
            this.chQ.offer(message);
            this.packagingService.wakeThread();
            return true;
        }
    }

    public boolean addSubscriber(Subscriber subscriber){
        this.subscriberSet.add(subscriber);
        return true;
    }

    public Message next(){
        synchronized (this.chQ){
            return this.chQ.peek();
        }
    }

    public static PubSubChannel getInstance(String name){
        ChannelMetadataIf channelMetadata = new ChannelMetadata(HashService.getInstance().getHashVal(name),name, ChannelType.PUBSUB);
        return new PubSubChannel(channelMetadata);
    }
}
