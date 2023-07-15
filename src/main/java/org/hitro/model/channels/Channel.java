package org.hitro.model.channels;

import org.hitro.model.iodtos.Message;
import org.hitro.model.metadatas.interfaces.ChannelMetadataIf;

public interface Channel {
    public boolean add(Message message);
    public Message next();
    public ChannelMetadataIf getMetadata();
}
