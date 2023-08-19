package org.hitro.model.channels;

import lombok.Getter;
import org.hitro.model.iodtos.Message;
import org.hitro.model.metadatas.ChannelMetadata;
import org.hitro.model.metadatas.interfaces.ChannelMetadataIf;
import org.hitro.publicinterfaces.ChannelType;
import org.hitro.services.HashService;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Queue;

@Getter
public class PollChannel implements Channel {
    private Queue<Message> chQ;
    private ChannelMetadataIf metadata;
    private PollChannel(ChannelMetadataIf channelMetadata){
            this.chQ = new LinkedList<>();
            this.metadata = channelMetadata;
    }
    public synchronized Message next(){
       return this.chQ.poll();
    }
    public synchronized boolean add(Message message){
        return this.chQ.offer(message);
    }

    public static PollChannel getInstance(String name){
        ChannelMetadataIf channelMetadata = new ChannelMetadata(HashService.getInstance().getHashVal(name),name, ChannelType.POLL);
        return new PollChannel(channelMetadata);
    }
}
