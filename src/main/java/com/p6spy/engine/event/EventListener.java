package com.p6spy.engine.event;

/**
 * Interface to be implemented by all classes which will listen for events
 */
public interface EventListener<E extends AbstractEvent> extends java.util.EventListener {

  /**
   * Determine whether this listener supports the given event type.
   */
  boolean supportsEventType(Class<E> eventType);

  /**
   * Called when an event of the specified type occurs
   *
   * @param event the event
   */
  void onEvent(E event);

}
