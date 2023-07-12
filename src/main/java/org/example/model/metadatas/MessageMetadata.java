package org.example.model.metadatas;

import lombok.*;
import org.example.model.metadatas.interfaces.MessageMetadataIf;

import java.util.Set;

@AllArgsConstructor
@Getter
public class MessageMetadata implements MessageMetadataIf {
    private String publisherId;
    private Set<String> messageTags;
    private String channelId;
    private String id;
    public boolean isValidMetadata(){
        return publisherId!=null &&
                messageTags != null &&
                channelId != null &&
                id != null;
    }

    @Override
    public void addMessageTag(String tag) {
        messageTags.add(tag);
    }
}
