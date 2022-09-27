package com.xmbsmdsj.influx4j.query;


import com.xmbsmdsj.influx4j.query.api.BuiltIn;
import com.xmbsmdsj.influx4j.query.api.Query;
import com.xmbsmdsj.influx4j.query.api.core.LambdaFilter;
import com.xmbsmdsj.influx4j.query.api.core.LogicalFilter;
import com.xmbsmdsj.influx4j.query.api.operation.BinaryOperation;
import com.xmbsmdsj.influx4j.query.api.reference.TagRef;
import com.xmbsmdsj.influx4j.query.api.type.StringValue;
import com.xmbsmdsj.influx4j.query.api.type.Value;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Instant;

@RunWith(JUnit4.class)
public class QueryBuilderTest {


    @Test
    public void testFilter() {
        Query.Builder b = Query.Builder.fromBucket("local");
        b
                .range(Instant.ofEpochMilli(1664273184106L), Instant.ofEpochMilli(1664273784106L))
                .filter(TagRef.of(BuiltIn.MEASUREMENT), BinaryOperation.EQ, new StringValue("sample_measure"));
        String s = b.build().toQueryString();

        Assert.assertEquals(
                """
                        from (bucket: "local")
                        |> range( start: 1664273184106, stop: 1664273784106 )
                        |> filter(fn: (r) =>  r._measurement  == "sample_measure")
                        """, s
        );
    }


    @Test
    public void testInCompleteRange() {
        var b = Query.Builder.fromBucket("local");
        b.range(Instant.ofEpochMilli(1664273184106L), null)
                .withMeasurement("sample_measure")
                .withField("amount");
        String s = b.build().toQueryString();
        Assert.assertEquals(
                """
                        from (bucket: "local")
                        |> range( start: 1664273184106 )
                        |> filter(fn: (r) =>  r._measurement  == "sample_measure")
                        |> filter(fn: (r) =>  r._field  == "amount")
                        """, s
        );
    }

    @Test
    public void testLogicalFilter() {
        var b = Query.Builder.fromBucket("local");
        b.range(Instant.ofEpochMilli(1664273184106L), null)
        .withField("amount")
                .filter(LogicalFilter.create().and(
                        new LambdaFilter(TagRef.of(BuiltIn.MEASUREMENT), BinaryOperation.EQ, Value.ofString("sample_measure")),
                        new LambdaFilter(TagRef.of("classification"), BinaryOperation.EQ, Value.ofString("class-1"))
                        ));
        String s = b.build().toQueryString();
        Assert.assertEquals(
                """    
                from (bucket: "local")
                |> range( start: 1664273184106 )
                |> filter(fn: (r) =>  r._field  == "amount")
                |> filter(fn: (r) =>  r._measurement  == "sample_measure" and  r.classification  == "class-1")
                """, s);
    }
}
