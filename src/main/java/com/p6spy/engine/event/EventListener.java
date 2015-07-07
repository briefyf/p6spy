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
