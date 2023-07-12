package org.example.model;

import lombok.Getter;
import org.example.model.metadatas.ChannelMetadata;
import org.example.model.metadatas.interfaces.ChannelMetadataIf;
import org.example.model.metadatas.interfaces.ChannelType;
import org.example.services.HashService;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Queue;

@Getter
public class Channel {
    private Queue<Message> chQ;
    private ChannelMetadataIf channelMetadata;
    public Channel(String name){
        try {
            HashService hashService = new HashService();
            chQ = new LinkedList<>();
            channelMetadata = new ChannelMetadata(hashService.getHashVal(name),name, ChannelType.POLL);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized Message next(){
       return this.chQ.poll();
    }
    public synchronized boolean add(Message message){
        return this.chQ.offer(message);
    }
}
