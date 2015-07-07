package com.p6spy.engine.event;
/**
 * Interface to be implemented by al classes which provide add/remove listener functionality
 */
public interface ListenerRegistry {
  void addListener(EventListener listener);

  void removeListener(EventListener listener);

  void removeAllListeners();
}
