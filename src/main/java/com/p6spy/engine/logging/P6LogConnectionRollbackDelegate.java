/*
 * #%L
 * P6Spy
 * %%
 * Copyright (C) 2013 P6Spy
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
package com.p6spy.engine.logging;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.common.P6LogQuery;
import com.p6spy.engine.common.StopWatch;
import com.p6spy.engine.event.EventPublisher;
import com.p6spy.engine.event.JDBCEvent;
import com.p6spy.engine.event.JDBCRollbackEvent;
import com.p6spy.engine.proxy.Delegate;

import java.lang.reflect.Method;

/**
 */
class P6LogConnectionRollbackDelegate implements Delegate {


  private final ConnectionInformation connectionInformation;
  private final EventPublisher eventPublisher;

  public P6LogConnectionRollbackDelegate(final ConnectionInformation connectionInformation, final EventPublisher eventPublisher) {
    this.connectionInformation = connectionInformation;
    this.eventPublisher = eventPublisher;
  }

  @Override
  public Object invoke(final Object proxy, final Object underlying, final Method method, final Object[] args) throws Throwable {
    long startTime = System.currentTimeMillis();
    StopWatch sw = new StopWatch().start();

    try {
      return method.invoke(underlying, args);
    } finally {
      JDBCEvent event = new JDBCRollbackEvent(underlying);
      event.setConnectionId(connectionInformation.getConnectionId());
      event.setStopWatch(sw);
      eventPublisher.publish(new JDBCRollbackEvent(underlying));
      P6LogQuery.logElapsed(connectionInformation.getConnectionId(), startTime, Category.ROLLBACK, "", "");
    }
  }
}
