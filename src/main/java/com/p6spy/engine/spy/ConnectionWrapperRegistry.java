package com.p6spy.engine.spy;

import java.util.Collection;

public interface ConnectionWrapperRegistry {
  void addConnectionWrapper(ConnectionWrapper wrapper);
  void removeConnectionWrapper(ConnectionWrapper wrapper);
  Collection<ConnectionWrapper> getConnectionWrappers();
}
