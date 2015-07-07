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
package com.p6spy.engine.event

import spock.lang.Specification

class DefaultEventMulticasterTest extends Specification {

  def multicaster = new DefaultEventMulticaster()
  def genericListener = new TestJDBCEventListener()
  def rsNextListner = new TestJDBCResultSetNextEventListener()

  def "listener registration"() {
    when:
    multicaster.addListener(genericListener)
    multicaster.addListener(rsNextListner)

    then:
    multicaster.allListeners.listeners.size() == 2
  }

  def "only unique instances of listeners allowed"() {
    when:
    multicaster.addListener(genericListener)
    multicaster.addListener(genericListener)
    multicaster.addListener(rsNextListner)

    then:
    multicaster.allListeners.listeners.size() == 2
    multicaster.allListeners.listeners.findAll {it.is(genericListener)}.size() == 1
    multicaster.allListeners.listeners.findAll {it.is(rsNextListner)}.size() == 1
  }

  def "listener filtering"() {
    given:
    multicaster.addListener(genericListener)
    multicaster.addListener(rsNextListner)
    def event = new JDBCCommitEvent(new Object())

    when:
    multicaster.multicast(event)

    then:
    !rsNextListner.invoked
    genericListener.invoked
  }

  def "listener filter caching"() {
    given:
    multicaster.addListener(genericListener)
    multicaster.addListener(rsNextListner)

    when:
    multicaster.multicast(new JDBCCommitEvent(new Object()))
    multicaster.multicast(new JDBCResultSetNextEvent(new Object()))
    multicaster.multicast(new JDBCResultSetNextEvent(new Object()))

    then:
    multicaster.listenerHolderCache.size() == 2
    multicaster.listenerHolderCache.get(JDBCResultSetNextEvent.class).getListeners().size() == 2
    multicaster.listenerHolderCache.get(JDBCCommitEvent.class).getListeners().size() == 1
  }


  class TestJDBCEventListener implements EventListener<JDBCEvent> {
    boolean invoked = false

    @Override
    boolean supportsEventType(Class<JDBCEvent> eventType) {
      JDBCEvent.isAssignableFrom(eventType)
    }

    @Override
    void onEvent(JDBCEvent event) {
      invoked = true
    }
  }

  class TestJDBCResultSetNextEventListener implements EventListener<JDBCResultSetNextEvent> {
    boolean invoked = false

    @Override
    boolean supportsEventType(Class<JDBCResultSetNextEvent> eventType) {
      JDBCResultSetNextEvent.isAssignableFrom(eventType)
    }

    @Override
    void onEvent(JDBCResultSetNextEvent event) {
      invoked = true
    }
  }
}
