package com.p6spy.engine.event;

/**
 * Default implementation of {@link EventPublisher}.  This class
 * delegates for an instance of {@link EventMulticaster}.
 */
public class DefaultEventPublisher implements EventPublisher {
  private EventMulticaster eventMulticaster;

  public DefaultEventPublisher(EventMulticaster eventMulticaster) {
    this.eventMulticaster = eventMulticaster;
  }

  @Override
  public void publish(AbstractEvent event) {
    eventMulticaster.multicast(event);

  }
}
