package com.xmbsmdsj.influx4j.query.api.reference;

import com.xmbsmdsj.influx4j.query.api.Tokens;
import com.xmbsmdsj.influx4j.query.api.utils.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FieldRef implements Ref {

  private final String fieldName;

  public static FieldRef of(String fieldName) {
    return new FieldRef(fieldName);
  }

  @Override
  public String name() {
    return fieldName;
  }

  @Override
  public String materialize() {
    return StringUtils.pad(Tokens.LAMBDA_PARAM + Tokens.FIELD_ACCESS + fieldName);
  }
}
