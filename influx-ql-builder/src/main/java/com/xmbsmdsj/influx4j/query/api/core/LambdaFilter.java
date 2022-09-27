package com.xmbsmdsj.influx4j.query.api.core;

import com.xmbsmdsj.influx4j.query.api.Tokens;
import com.xmbsmdsj.influx4j.query.api.operation.BinaryOperation;
import com.xmbsmdsj.influx4j.query.api.reference.Ref;
import com.xmbsmdsj.influx4j.query.api.type.Value;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class LambdaFilter implements Filter {
  private final Ref ref;
  private final BinaryOperation binaryOperation;
  private final Value value;

  @Override
  public String materialize() {
    return Tokens.FILTER_PREFIX + binaryOperation.materialize(ref, value) + Tokens.FILTER_SUFFIX;
  }

  @Override
  public String condition() {
    return binaryOperation.materialize(ref, value);
  }
}
