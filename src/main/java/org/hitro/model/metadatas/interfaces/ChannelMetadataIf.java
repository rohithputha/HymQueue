package org.hitro.model.metadatas.interfaces;

import org.hitro.publicinterfaces.ChannelType;

public interface ChannelMetadataIf extends MetadataIf {
    public String getName();

    public ChannelType getChannelType();
}

