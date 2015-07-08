package com.p6spy.engine.proxy;

import com.p6spy.engine.event.EventPublisher;
import com.p6spy.engine.event.EventPublisherAware;

public abstract class AbstractDelegate implements Delegate, EventPublisherAware {
  private EventPublisher eventPublisher;

  @Override
  public void setEventPublisher(final EventPublisher eventPublisher) {

    this.eventPublisher = eventPublisher;
  }

  public EventPublisher getEventPublisher() {
    return eventPublisher;
  }
}
