package org.example.model.metadatas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.metadatas.interfaces.ChannelMetadataIf;
import org.example.model.metadatas.interfaces.ChannelType;

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
