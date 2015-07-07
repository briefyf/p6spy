/*
 * #%L
 * P6Spy
 * %%
 * Copyright (C) 2002 - 2015 P6Spy
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.p6spy.engine.event;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation of {@link EventMulticaster}.
 * <p/>
 * This implementation multicasts all events to all registered listeners which support the given event type.  The
 * listeners are invoked in the calling thread.
 */
public class DefaultEventMulticaster implements EventMulticaster {
  private final ListenerHolder allListeners = new ListenerHolder();
  private final Map<Class<? extends AbstractEvent>, ListenerHolder> listenerHolderCache =
    new ConcurrentHashMap<Class<? extends AbstractEvent>, ListenerHolder>();

  @Override
  public void addListener(final EventListener listener) {
    synchronized (allListeners) {
      allListeners.add(listener);
      listenerHolderCache.clear();
    }
  }

  @Override
  public void removeListener(final EventListener listener) {
    synchronized (allListeners) {
      allListeners.remove(listener);
      listenerHolderCache.clear();
    }
  }

  @Override
  public void removeAllListeners() {
    synchronized (allListeners) {
      allListeners.clear();
      listenerHolderCache.clear();
    }
  }

  @Override
  public void multicast(final AbstractEvent event) {
    for( EventListener listener : getListeners(event)) {
      listener.onEvent(event);
    }
  }

  protected Collection<EventListener> getListeners(final AbstractEvent event) {

    // lookup from cache
    ListenerHolder listenerHolder = listenerHolderCache.get(event.getClass());
    if( listenerHolder == null ) {
      listenerHolder = new ListenerHolder();

      Collection<EventListener> candidateListeners;

      // copy from master list
      synchronized (listenerHolder) {
        candidateListeners = new HashSet<EventListener>(allListeners.getListeners());
      }

      // find listeners that support the given event type
      for( EventListener listener : candidateListeners ) {
        if( listener.supportsEventType(event.getClass())) {
          listenerHolder.add(listener);
        }
      }

      // add to cache
      listenerHolderCache.put(event.getClass(), listenerHolder);
    }

    return listenerHolder.getListeners();
  }

  private class ListenerHolder {
    private final Collection<EventListener> listeners = new HashSet<EventListener>();

    void add(EventListener listener) {
      listeners.add(listener);
    }

    Collection<EventListener> getListeners() {
      return listeners;
    }

    public void remove(EventListener listener) {
      listeners.remove(listener);
    }

    public void clear() {
      listeners.clear();
    }
  }
}
