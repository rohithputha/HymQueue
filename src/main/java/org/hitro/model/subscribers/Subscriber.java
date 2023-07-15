package org.hitro.model.subscribers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hitro.model.subscribers.SubscriberFunction;

@AllArgsConstructor
@Getter
public class Subscriber {

    private final SubscriberFunction callbackFunction;

}
