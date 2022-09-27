package com.xmbsmdsj.influx4j.query.api.type;

import java.math.BigDecimal;

public sealed interface Value permits StringValue, NumberValue, BooleanValue{
  String value();

  static StringValue ofString(String s) {
    return new StringValue(s);
  }

  static NumberValue ofLong(long l) {
    return new NumberValue(BigDecimal.valueOf(l));
  }

  static NumberValue ofDouble(double d) {
    return new NumberValue(BigDecimal.valueOf(d));
  }

  static BooleanValue TRUE = new BooleanValue(true);
  static BooleanValue FALSE = new BooleanValue(false);
}
