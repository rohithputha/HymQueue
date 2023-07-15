package org.hitro.model.metadatas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hitro.model.metadatas.interfaces.SubscriberMetadataIf;

@AllArgsConstructor
@Getter
public class SubscriberMetadata implements SubscriberMetadataIf {
    private String id;
    private String channelId;

    @Override
    public boolean isValidMetadata() {
        return id!=null && id.length()>0 &&
                channelId != null && channelId.length()>0;
    }

}
