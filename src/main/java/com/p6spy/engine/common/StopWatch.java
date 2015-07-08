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
package com.p6spy.engine.common;

public class StopWatch {
  private Long startTimeNs = null;
  private Long startTimeMs = null;
  private Long endTimeNs = null;

  public StopWatch start() {
    startTimeNs = System.nanoTime();
    startTimeMs = System.currentTimeMillis();
    return this;
  }

  public StopWatch stop() {
    endTimeNs = System.nanoTime();
    return this;
  }

  public boolean started() {
    return startTimeNs != null;
  }

  public boolean stopped() {
    return endTimeNs != null;
  }

  public long durationInWholeMs() {
    return (long) Math.floor(durationInNs() / 1000);
  }

  public double durationInFractionalMs() {
    return ((double)durationInNs()) / ((double)1000);
  }

  public long durationInNs() {
    if( startTimeNs == null ) {
      throw new IllegalStateException("not started");
    }
    if( endTimeNs == null ) {
      throw new IllegalStateException("still running");
    }
    return endTimeNs - startTimeNs;
  }

  public long startTimeMs() {
    if( startTimeMs == null ) {
      throw new IllegalStateException("not started");
    }
    return startTimeMs;
  }
}
