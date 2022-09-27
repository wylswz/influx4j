package com.xmbsmdsj.influx4j.sample;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Measurement(name = "sample_measure")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleMeasure {
  @Column(timestamp = true)
  Instant fromTime;

  @Column(timestamp = true)
  Instant toTime;

  @Column(tag = true)
  String classification;

  @Column(tag = true)
  String customSubjectId;

  @Column BigDecimal amount;
  @Column BigDecimal replica;
}
