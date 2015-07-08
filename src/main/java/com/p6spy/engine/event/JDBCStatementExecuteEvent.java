package com.p6spy.engine.event;

public class JDBCStatementExecuteEvent extends JDBCEvent {
  /**
   * Constructs a new Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public JDBCStatementExecuteEvent(Object source) {
    super(source);
  }
}
