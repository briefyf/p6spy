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

/**
 * Encapsulates access to the event publisher and listener registry
 */
public class EventManager implements EventPublisher, ListenerRegistry {

  private EventMulticaster eventMulticaster;
  private EventPublisher eventPublisher;

  public EventManager() {
    eventMulticaster = new DefaultEventMulticaster();
    eventPublisher = new DefaultEventPublisher(eventMulticaster);
  }

  EventPublisher getEventPublisher() {
    return eventPublisher;
  }

  ListenerRegistry getListenerRegistry() {
    return eventMulticaster;
  }

  void setEventMulticaster(EventMulticaster eventMulticaster) {
    this.eventMulticaster = eventMulticaster;
  }

  void setEventPublisher(EventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  @Override
  public void publish(AbstractEvent event) {
    eventPublisher.publish(event);
  }

  @Override
  public void addListener(EventListener listener) {
    eventMulticaster.addListener(listener);
  }

  @Override
  public void removeListener(EventListener listener) {
    eventMulticaster.removeListener(listener);
  }

  @Override
  public void removeAllListeners() {
    eventMulticaster.removeAllListeners();
  }
}
