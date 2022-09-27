package com.xmbsmdsj.influx4j.query.internal;

public enum ArgType {
  STRING {
    @Override
    boolean trimQuotes() {
      return false;
    }
  },
  SYMBOL {
    @Override
    boolean trimQuotes() {
      return true;
    }
  };

  abstract boolean trimQuotes();
}
