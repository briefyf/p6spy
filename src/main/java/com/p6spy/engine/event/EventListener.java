package com.p6spy.engine.event;

/**
 * Interface to be implemented by all classes which will listen for events
 */
public interface EventListener<E extends AbstractEvent> extends java.util.EventListener {

  void onEvent(E event);

}
