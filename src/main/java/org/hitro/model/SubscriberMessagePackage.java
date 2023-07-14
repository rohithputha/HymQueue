package org.hitro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SubscriberMessagePackage {
    private Subscriber subscriber;
    private Message message;

}
