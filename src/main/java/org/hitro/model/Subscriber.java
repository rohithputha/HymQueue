package org.hitro.model;

import lombok.AllArgsConstructor;
import org.hitro.model.interfaces.SubscriberFunction;

@AllArgsConstructor
public class Subscriber {

    private final SubscriberFunction callbackFunction;

}
