package com.p6spy.engine.event;

/**
 * Interface for all classes which can register {@link EventListener}
 * objects and publish events to them.
 */
public interface EventMulticaster {

  void addListener(EventListener listener);

  void removeListener(EventListener listener);

  void removeAllListeners();

  void multicast(AbstractEvent abstractEvent);

}
