package org.hitro.services;

import org.hitro.exceptions.HymQueueException;
import org.hitro.model.*;
import org.hitro.model.interfaces.Channel;
import org.hitro.model.interfaces.SubscriberFunction;
import org.hitro.model.metadatas.interfaces.ChannelType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HymQueue {

    private Map<String, Channel> channelMap;
    public HymQueue(){
        this.channelMap = new HashMap<>();
        MessageDistributorService.assignExecutorTasks();
    }

    public synchronized boolean createChannel(String name, ChannelType channelType){
        // ideally should be factory method and factory pattern
        if(!channelMap.containsKey(name)){
            if(channelType == ChannelType.POLL){
                channelMap.put(name,new PollChannel(name));
            }
            else if(channelType == ChannelType.PUBSUB){
                channelMap.put(name, new PubSubChannel(name));
            }
            return true;
        }
        throw new HymQueueException("Channel with the same name already exists");
    }

    public <T> boolean add(T data, String channelName,String publisherId){
        try {
            Message newMessage = new Message(data, publisherId, new HashSet<>(), this.channelMap.get(channelName).getMetadata().getId());
            this.channelMap.get(channelName).add(newMessage);
            return true;
        }
        catch (Exception e){
            //log the exception here
            throw new HymQueueException("Failed to add data to the queue.",e);
        }
    }

    public <T> HymOutput<T,Class> get(String channelName){
        try{
            return this.channelMap.get(channelName).next().getData();

        }
        catch (NullPointerException ne){
            return null;
        }
        catch (Exception e){
            throw new HymQueueException("Failed to fetch  data from the queue.");
        }
    }
    public void addSubscriber(String channelName, SubscriberFunction subscriberFunction){
       Channel storedChannel = this.channelMap.get(channelName);
       PubSubChannel pubSubChannel = null;
       if(storedChannel == null){
           throw new NullPointerException("Map does not contain the channel name");
       }
       if(storedChannel.getMetadata().getChannelType() == ChannelType.PUBSUB){
           pubSubChannel = (PubSubChannel) storedChannel;
       }
       if(pubSubChannel==null){
           throw new NullPointerException("Request channel does not support subscribers");
       }

       //** we should have a metadata field for subscriber as well...
       pubSubChannel.addSubscriber(new Subscriber(subscriberFunction));
    }

    public SubscriberMessagePackage getSubscriberMessagePackage(String channelName){
        PubSubChannel pubSubChannel = (PubSubChannel) channelMap.get(channelName);
        return pubSubChannel.get();
    }
}
