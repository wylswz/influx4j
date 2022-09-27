package com.xmbsmdsj.influx4j.query.api.utils;

import com.xmbsmdsj.influx4j.query.api.Tokens;

import java.time.Instant;

public class StringUtils {
  public static void appendLine(StringBuilder sb, String value) {
    sb.append(value).append(System.lineSeparator());
  }

  public static void appendFluxLine(StringBuilder sb, String flux) {
    sb.append(Tokens.FLUX_CONCAT).append(flux).append(System.lineSeparator());
  }

  public static String passParam(String paramName, String paramVal, Boolean prefixComma) {
    return (prefixComma ? ", " : "") + paramName + ": " + paramVal;
  }

  public static String prettifyInstant(Instant instant) {
    return Long.valueOf(instant.toEpochMilli()).toString();
  }

  public static String pad(String s) {
    return  " " + s + " ";
  }
}
