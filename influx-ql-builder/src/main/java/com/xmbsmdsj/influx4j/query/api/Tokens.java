package com.xmbsmdsj.influx4j.query.api;

public class Tokens {
  public static final String FROM = "from";
  public static final String LITERAL_TRUE = "true";

  public static final String LITERAL_FALSE = "false";
  public static final String LAMBDA_PARAM = "r";

  public static final String FIELD_ACCESS = ".";
  public static final String TAG_ACCESS = FIELD_ACCESS;
  public static final String FILTER_PREFIX = "filter(fn: (%s) => ".formatted(Tokens.LAMBDA_PARAM);
  public static final String FILTER_SUFFIX = ")";

  public static final String RANGE_START = "start";
  public static final String RANGE_STOP = "stop";
  public static final String RANGE_PREFIX = "range( ";
  public static final String RANGE_SUFFIX = " )";

  public static final String FLUX_CONCAT = "|> ";

  public static final String FUNC_ARG_LIST_START = "( ";
  public static final String FUNC_ARG_LIST_END = " )";
}
