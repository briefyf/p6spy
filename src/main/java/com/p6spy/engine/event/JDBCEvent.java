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

import com.p6spy.engine.common.PreparedStatementInformation;
import com.p6spy.engine.common.SQLWrapper;
import com.p6spy.engine.common.StopWatch;
/**
 * Base class for JDBC events
 */
public class JDBCEvent extends AbstractEvent {
  private StopWatch stopWatch;
  private int connectionId;
  private Throwable thrownException;
  private SQLWrapper sqlWrapper;

  /**
   * Constructs a new Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public JDBCEvent(Object source) {
    super(source);
  }

  public int getConnectionId() {
    return connectionId;
  }

  public StopWatch getStopWatch() {
    return stopWatch;
  }

  public JDBCEvent withConnectionId(final int connectionId) {
    this.connectionId = connectionId;
    return this;
  }

  public JDBCEvent withStopWatch(final StopWatch stopWatch) {
    this.stopWatch = stopWatch;
    if (!stopWatch.stopped()) {
      stopWatch.stop();
    }
    return this;
  }

  public JDBCEvent withThrownException(final Throwable thrownException) {
    this.thrownException = thrownException;
    return this;
  }

  public JDBCEvent withSQLWrapper(SQLWrapper sqlWrapper) {
    this.sqlWrapper = sqlWrapper;
    return this;
  }
}
