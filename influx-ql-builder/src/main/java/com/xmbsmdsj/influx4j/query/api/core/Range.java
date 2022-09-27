package com.xmbsmdsj.influx4j.query.api.core;

import com.xmbsmdsj.influx4j.query.api.Tokens;
import com.xmbsmdsj.influx4j.query.api.utils.StringUtils;
import com.xmbsmdsj.influx4j.query.internal.ArgType;
import com.xmbsmdsj.influx4j.query.internal.IArgs;
import com.xmbsmdsj.influx4j.query.internal.IFunction;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public final class Range implements IFlux, IFunction {

  private final Instant start;
  private final Instant end;

  @Override
  public String materialize() {
    if (start == null && end == null) {
      throw new IllegalArgumentException("Either start or end must be non-null");
    }
    return materializeFunction();
  }

  @Override
  public String name() {
    return "range";
  }

  @Override
  public List<IArgs> arguments() {
    List<IArgs> args = new LinkedList<>();
    if (start != null) {
      args.add(new IArgs(Tokens.RANGE_START, StringUtils.prettifyInstant(start), ArgType.SYMBOL));
    }
    if (end != null) {
      args.add(new IArgs(Tokens.RANGE_STOP, StringUtils.prettifyInstant(end), ArgType.SYMBOL));
    }
    return args;
  }
}
