package org.hitro.services;

import org.hitro.exceptions.HymQueueException;
import org.hitro.model.Channel;
import org.hitro.model.Message;

import java.util.HashSet;
import java.util.Map;

public class Queue {

    private Map<String, Channel> channelMap;

    public synchronized boolean createChannel(String name){
        if(!channelMap.containsKey(name)){
            channelMap.put(name,new Channel(name));
            return true;
        }
        throw new HymQueueException("Channel with the same name already exists");
    }

    public <T> boolean addToQueue(T data, String channelName,String publisherId){
        try {
            Message newMessage = new Message(data, publisherId, new HashSet<>(), this.channelMap.get(channelName).getChannelMetadata().getId());
            return true;
        }
        catch (Exception e){
            //log the exception here
            throw new HymQueueException("Failed to add data to the queue.",e);
        }
    }


}
