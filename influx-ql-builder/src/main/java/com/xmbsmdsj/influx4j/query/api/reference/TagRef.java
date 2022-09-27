package com.xmbsmdsj.influx4j.query.api.reference;

import com.xmbsmdsj.influx4j.query.api.Tokens;
import com.xmbsmdsj.influx4j.query.api.utils.StringUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagRef implements Ref {

  private final String tagName;

  public static TagRef of(String tagName) {
    return new TagRef(tagName);
  }


  @Override
  public String name() {
    return tagName;
  }

  @Override
  public String materialize() {
    return StringUtils.pad(Tokens.LAMBDA_PARAM + Tokens.TAG_ACCESS + tagName);
  }
}
