package org.hitro.model.interfaces;

import org.hitro.model.Message;

@FunctionalInterface
public interface SubscriberFunction {
    public Message get();

}
