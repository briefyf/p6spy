package com.p6spy.engine.event;

/**
 * Interface for all classes which can publish events.
 */
public interface EventPublisher {

  void publish(AbstractEvent event);
}
