package org.hitro.services;

import org.hitro.model.channels.Channel;
import org.hitro.model.channels.PollChannel;
import org.hitro.model.channels.PubSubChannel;
import org.hitro.publicinterfaces.ChannelType;

public class HymQueueHelper {
    public static Channel getChannelInstance(ChannelType channelType, String name){
        if(channelType == null || channelType == ChannelType.POLL){
            return PollChannel.getInstance(name);
        }
        else if(channelType == ChannelType.PUBSUB){
            return PubSubChannel.getInstance(name);
        }
        return null;
    }

    public static PubSubChannel convertToPubSubChannel(Channel storedChannel){
        if(storedChannel == null) return null;
        if(storedChannel.getMetadata().getChannelType() == ChannelType.PUBSUB) return (PubSubChannel) storedChannel;
        return null;
    }
}
