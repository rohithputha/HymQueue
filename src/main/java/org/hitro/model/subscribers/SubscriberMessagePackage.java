package org.hitro.model.subscribers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hitro.model.iodtos.Message;

@AllArgsConstructor
@Getter
public class SubscriberMessagePackage {
    private Subscriber subscriber;
    private Message message;

}
