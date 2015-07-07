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
