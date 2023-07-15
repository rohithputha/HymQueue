package org.hitro.model.subscribers;

import org.hitro.model.iodtos.HymOutput;

@FunctionalInterface
public interface SubscriberFunction {
    public void consume(HymOutput hymOutput);

}
