package org.hitro.model.interfaces;

import org.hitro.model.HymOutput;
import org.hitro.model.Message;
import org.hitro.model.metadatas.interfaces.ChannelMetadataIf;

public interface Channel {
    public boolean add(Message message);
    public Message next();
    public ChannelMetadataIf getMetadata();
}
