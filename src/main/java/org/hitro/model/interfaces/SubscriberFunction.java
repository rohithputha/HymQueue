package org.hitro.model.interfaces;

import org.hitro.model.HymOutput;
import org.hitro.model.Message;

@FunctionalInterface
public interface SubscriberFunction {
    public void consume(HymOutput hymOutput);

}
