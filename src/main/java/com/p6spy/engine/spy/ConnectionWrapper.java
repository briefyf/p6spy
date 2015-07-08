package com.p6spy.engine.spy;

import java.sql.Connection;

/**
 * Interface to be implemented by classes which wrap connections.  Connection wrappers are the proxies around
 * connections which enable P6Spy module functionality.
 */
public interface ConnectionWrapper {

  Connection wrapConnection(final Connection connection);
}
