package com.xmbsmdsj.influx4j.query;


import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.domain.Query;
import com.influxdb.query.FluxTable;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
@Ignore
public class TestPlainQuery {

    private final InfluxDBClient client = InfluxDBClientFactory.create(
            "http://127.0.0.1:8086", "root", "admin".toCharArray()
    );

    @Test
    public void testQuery() {
        QueryApi queryApi = client.getQueryApi();
        List<FluxTable> res = queryApi.query(
                """
                        from (bucket: "local")\s
                        |> range(start: -50m)
                        |> filter(fn: (r) => r._measurement == "sample_measure")
                        """
                , "local"
        );

    }



}
