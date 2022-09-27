package com.xmbsmdsj.influx4j.query.api.core.aggregate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Collections;
import java.util.List;

import com.xmbsmdsj.influx4j.query.api.core.IFlux;

import com.xmbsmdsj.influx4j.query.internal.ArgType;
import com.xmbsmdsj.influx4j.query.internal.IArgs;
import com.xmbsmdsj.influx4j.query.internal.IFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Window implements IFlux, IFunction {

  private final TemporalAmount windowSize;

  public static Window every(long num, ChronoUnit unit) {
    return new Window(Duration.of(num, unit));
  }

  public static Window everyMinutes(long minutes) {
    return every(minutes, ChronoUnit.MINUTES);
  }

  public static Window everySeconds(long seconds) {
    return every(seconds, ChronoUnit.SECONDS);
  }

  public static Window everyHours(long hour) {
    return every(hour, ChronoUnit.HOURS);
  }

  public static Window everyDays(long days) {
    return every(days, ChronoUnit.DAYS);
  }

  @Override
  public String name() {
    return "window";
  }

  @Override
  public List<IArgs> arguments() {
    return Collections.singletonList(
        new IArgs(
            "every", Long.valueOf(windowSize.get(ChronoUnit.MILLIS)).toString(), ArgType.SYMBOL));
  }

  @Override
  public String materialize() {
    return materializeFunction();
  }
}
