package com.xmbsmdsj.influx4j.query.api;

import com.xmbsmdsj.influx4j.query.api.core.From;
import com.xmbsmdsj.influx4j.query.api.core.IFlux;
import com.xmbsmdsj.influx4j.query.api.core.LambdaFilter;
import com.xmbsmdsj.influx4j.query.api.core.Range;
import com.xmbsmdsj.influx4j.query.api.operation.BinaryOperation;
import com.xmbsmdsj.influx4j.query.api.reference.Ref;
import com.xmbsmdsj.influx4j.query.api.reference.TagRef;
import com.xmbsmdsj.influx4j.query.api.type.StringValue;
import com.xmbsmdsj.influx4j.query.api.type.Value;
import com.xmbsmdsj.influx4j.query.api.utils.StringUtils;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class Query {

  private final String bucket;
  private final Range range;
  private final List<IFlux> fluxes;

  public static class Builder {
    private final String bucket;
    private Range range;
    private List<IFlux> fluxes;

    private Builder(String bucket) {
      this.bucket = bucket;
      this.fluxes = new LinkedList<>();
    }

    public static Builder fromBucket(String bucket) {
      return new Builder(bucket);
    }

    public Builder range(Instant from, Instant to) {
      this.range = new Range(from, to);
      return this;
    }

    public Builder filter(Ref target, BinaryOperation op, Value value) {
      this.fluxes.add(new LambdaFilter(target, op, value));
      return this;
    }

    public Builder filterOnTag(String tag, BinaryOperation op, Value value) {
      return filter(TagRef.of(tag), op, value);
    }

    public Builder withMeasurement(String measurement) {
      return filterOnTag(BuiltIn.MEASUREMENT, BinaryOperation.EQ, new StringValue(measurement));
    }

    public Builder withField(String field) {
      return filterOnTag(BuiltIn.FIELD, BinaryOperation.EQ, new StringValue(field));
    }

    public Query build() {
      return new Query(bucket, range, fluxes);
    }
  }

  public String toQueryString() {
    var sb = new StringBuilder();
    // from phase
    if (bucket == null) {
      throw new IllegalArgumentException("Bucket must not be null");
    }
    StringUtils.appendLine(sb, From.fromBucket(bucket).materialize());
    if (range == null) {
      throw new IllegalArgumentException("Range must not be null");
    }
    StringUtils.appendFluxLine(sb, range.materialize());

    // IFlux phase
    for (IFlux flux : fluxes) {
      StringUtils.appendFluxLine(sb, flux.materialize());
    }
    return sb.toString();
  }
}
