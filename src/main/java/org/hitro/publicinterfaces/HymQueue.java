package org.hitro.publicinterfaces;

import org.hitro.exceptions.HymQueueException;
import org.hitro.model.channels.PubSubChannel;
import org.hitro.model.channels.Channel;
import org.hitro.model.iodtos.Message;
import org.hitro.model.subscribers.Subscriber;
import org.hitro.model.subscribers.SubscriberFunction;
import org.hitro.services.HymQueueHelper;
import org.hitro.services.messagesubscriberdistribution.MessageDistributorService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HymQueue {

    private Map<String, Channel> channelMap;
    public HymQueue(){
        this.channelMap = new HashMap<>();
        MessageDistributorService.assignExecutorTasks();// have a better init of the thread executors
    }

    public synchronized boolean createChannel(String name, ChannelType channelType){
        if(!channelMap.containsKey(name)){
           this.channelMap.put(name, HymQueueHelper.getChannelInstance(channelType,name));
           return true;
        }
        throw new HymQueueException("Channel with the same name already exists");
    }

    public <T> boolean add(T data, String channelName,String publisherId){
        try {
            Message newMessage = Message.getInstance(data, publisherId, new HashSet<>(), this.channelMap.get(channelName).getMetadata().getId());
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
    public void addSubscriber(String subscriberId, String channelName, SubscriberFunction subscriberFunction){
        try{
            Channel storedChannel = this.channelMap.get(channelName);
            PubSubChannel pubSubChannel = HymQueueHelper.convertToPubSubChannel(storedChannel);
            if (pubSubChannel == null) throw new NullPointerException("Channel provided does not exist or does not support subscribers");
            pubSubChannel.addSubscriber(Subscriber.getInstance(subscriberId,pubSubChannel,subscriberFunction));
        }
        catch (Exception ne){
            throw new HymQueueException("Subscriber addition failed",ne);
        }
    }
}
