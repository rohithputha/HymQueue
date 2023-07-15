package org.hitro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hitro.model.interfaces.SubscriberFunction;

@AllArgsConstructor
@Getter
public class Subscriber {

    private final SubscriberFunction callbackFunction;

}
