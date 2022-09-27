package com.xmbsmdsj.influx4j.query.api.core;

public class From {

  private String bucket;

  public static From fromBucket(String bucket) {
    var res = new From();
    res.bucket = bucket;
    return res;
  }

  public String materialize() {
    return "from (bucket: \"%s\")".formatted(bucket);
  }
}
