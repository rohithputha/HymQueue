package org.hitro.model;

import lombok.Getter;
import org.hitro.model.interfaces.Channel;
import org.hitro.model.metadatas.ChannelMetadata;
import org.hitro.model.metadatas.interfaces.ChannelMetadataIf;
import org.hitro.model.metadatas.interfaces.ChannelType;
import org.hitro.services.HashService;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Queue;

@Getter
public class PollChannel implements Channel {
    private Queue<Message> chQ;
    private ChannelMetadataIf metadata;
    public PollChannel(String name){
        try {
            HashService hashService = new HashService();
            this.chQ = new LinkedList<>();
            this.metadata = new ChannelMetadata(hashService.getHashVal(name),name, ChannelType.POLL);
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
