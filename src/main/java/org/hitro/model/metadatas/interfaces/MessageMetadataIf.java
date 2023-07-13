package org.hitro.model.metadatas.interfaces;

import java.util.List;
import java.util.Set;

public interface MessageMetadataIf extends MetadataIf {
    public Set<String> getMessageTags();

    public String getChannelId();

    public void addMessageTag(String tag);

    public String getPublisherId();

}
