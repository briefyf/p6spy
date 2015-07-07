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
