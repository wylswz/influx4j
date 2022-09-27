package com.xmbsmdsj.influx4j.query.api.operation;

import com.xmbsmdsj.influx4j.query.api.reference.Ref;
import com.xmbsmdsj.influx4j.query.api.type.Value;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BinaryOperation {
  EQ("=="), LT("<"), LTE("<="),
  GT(">"), GTE(">=");

  private final String symbol;


  public String materialize(Ref ref, Value v) {
    return ref.materialize() + " " + symbol + " " + v.value();
  }
}
