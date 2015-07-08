package com.p6spy.engine.logging;

import com.p6spy.engine.event.EventListener;
import com.p6spy.engine.event.JDBCEvent;

public class LoggingEventListener implements EventListener<JDBCEvent> {
  @Override
  public boolean supportsEventType(Class<JDBCEvent> eventType) {
    return JDBCEvent.class.isAssignableFrom(eventType.getClass());
  }

  @Override
  public void onEvent(JDBCEvent event) {
    // TODO generate log message
  }
}
