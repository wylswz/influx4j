package com.xmbsmdsj.influx4j.query.api.core;

public sealed interface Filter extends IFlux
        permits LambdaFilter, LogicalFilter {

    String condition();
}
