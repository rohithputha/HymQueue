package org.hitro.model.subscribers;

import org.hitro.publicinterfaces.HymOutput;

@FunctionalInterface
public interface SubscriberFunction {
    public void consume(HymOutput hymOutput);

}
