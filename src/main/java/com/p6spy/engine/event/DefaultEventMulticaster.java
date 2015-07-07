package com.p6spy.engine.event;

/**
 * Default implementation of {@link EventMulticaster}.
 *
 * This implementation multicasts all events to all registered listeners.  It is
 * up to the listeners to ignore events that they are not interested in.
 *
 * Note: All listeners are invoked in the calling thread.
 */
public class DefaultEventMulticaster implements EventMulticaster {
  @Override
  public void addListener(EventListener listener) {

  }
  @Override
  public void removeListener(EventListener listener) {

  }
  @Override
  public void removeAllListeners() {

  }
  @Override
  public void multicast(AbstractEvent abstractEvent) {

  }
}
