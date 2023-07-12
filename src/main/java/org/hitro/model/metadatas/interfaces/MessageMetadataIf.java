package org.hitro.model.metadatas.interfaces;

import java.util.List;

public interface MessageMetadataIf extends MetadataIf {
    public List<String> getMessageTags();

    public String getChannelId();

    public void addMessageTag(String tag);

    public String getPublisherId();
}
