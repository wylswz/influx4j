package com.xmbsmdsj.influx4j.query.api.core;

import com.xmbsmdsj.influx4j.query.api.Tokens;
import com.xmbsmdsj.influx4j.query.api.utils.StringUtils;
import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public final class Range implements IFlux {

  private final Instant start;
  private final Instant end;

  @Override
  public String materialize() {
    if (start == null && end == null) {
      throw new IllegalArgumentException("Either start or end must be non-null");
    }
    return Tokens.RANGE_PREFIX
        + (start == null
            ? ""
            : StringUtils.passParam(Tokens.RANGE_START, StringUtils.prettifyInstant(start), false))
        + (end == null
            ? ""
            : StringUtils.passParam(Tokens.RANGE_STOP, StringUtils.prettifyInstant(end), true))
        + Tokens.RANGE_SUFFIX;
  }
}
