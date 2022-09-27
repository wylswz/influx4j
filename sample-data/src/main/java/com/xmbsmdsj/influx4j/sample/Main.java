package com.xmbsmdsj.influx4j.sample;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Main {

  private static final String BUCKET = "local";
  private static final String ORG = "local";

  public static void main(String[] args) {
    InfluxDBClient client =
        InfluxDBClientFactory.create("http://127.0.0.1:8086", "root", "admin".toCharArray());
    WriteApiBlocking writeApiBlocking = client.getWriteApiBlocking();

    for (int i = 0; i < 3000; i++) {
      SampleMeasure sm =
          SampleMeasure.builder()
              .fromTime(Instant.now())
              .toTime(Instant.now().plus(10, ChronoUnit.SECONDS))
              .amount(BigDecimal.valueOf(i % 100))
              .replica(BigDecimal.valueOf(i % 3))
              .classification("class-" + i % 4)
              .customSubjectId("" + i % 13)
              .build();
      writeApiBlocking.writeMeasurement(BUCKET, ORG, WritePrecision.MS, sm);
    }
  }
}
