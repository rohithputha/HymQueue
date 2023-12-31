package org.hitro.model.subscribers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hitro.model.channels.Channel;
import org.hitro.model.metadatas.SubscriberMetadata;
import org.hitro.model.metadatas.interfaces.SubscriberMetadataIf;
import org.hitro.model.subscribers.SubscriberFunction;
import org.hitro.services.HashService;

import java.security.NoSuchAlgorithmException;

@Getter
public class Subscriber {

    private final SubscriberMetadataIf subscriberMetadata;
    private final SubscriberFunction callbackFunction;

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o ==  null || this.getClass()!=o.getClass()){
            return false;
        }
        Subscriber s= (Subscriber) o;
        return s.hashCode() ==  this.hashCode();
    }

    @Override
    public  int hashCode(){
       return subscriberMetadata.getChannelId().hashCode();
    }

    private Subscriber(SubscriberMetadataIf subscriberMetadata, SubscriberFunction subscriberFunction ){
        this.subscriberMetadata = subscriberMetadata;
        this.callbackFunction = subscriberFunction;
    }

    public static Subscriber getInstance(String subscriberId, Channel parentChannel, SubscriberFunction subscriberFunction) throws NoSuchAlgorithmException {
        String parentChannelId = parentChannel.getMetadata().getId();
        SubscriberMetadataIf subscriberMetadata = new SubscriberMetadata(HashService.getInstance().getHashVal(subscriberId+parentChannelId),parentChannelId);
        return new Subscriber(subscriberMetadata,subscriberFunction);
    }

}
