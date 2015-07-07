package com.p6spy.engine.event;

/**
 * Base class for JDBC events
 */
public class JDBCEvent extends AbstractEvent {
  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public JDBCEvent(Object source) {
    super(source);
  }
}
