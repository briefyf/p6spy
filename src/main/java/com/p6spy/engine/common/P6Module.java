package com.p6spy.engine.common;

import com.p6spy.engine.spy.ConnectionWrapperRegistry;

public interface P6Module {
  void registerOptions(OptionsRegistry optionsRegistry);
  void registerConnectionWrapper(ConnectionWrapperRegistry connectionWrapperRegistry);
}
