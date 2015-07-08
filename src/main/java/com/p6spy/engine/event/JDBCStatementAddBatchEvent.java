package com.p6spy.engine.event;

public class JDBCStatementAddBatchEvent extends JDBCEvent {
  /**
   * Constructs a new Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public JDBCStatementAddBatchEvent(Object source) {
    super(source);
  }
}
