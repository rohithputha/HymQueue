package org.hitro.model.metadatas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hitro.model.metadatas.interfaces.ChannelMetadataIf;
import org.hitro.publicinterfaces.ChannelType;

@Getter
@AllArgsConstructor
public class ChannelMetadata implements ChannelMetadataIf {
    private String id;
    private String name;
    private ChannelType channelType;

    @Override
    public boolean isValidMetadata() {
        return id != null &&
                name != null;
    }
}
