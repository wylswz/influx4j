package com.xmbsmdsj.influx4j.query.internal;


public record IArgs(String name, String value, ArgType type) {

    public String value() {
        return type.trimQuotes() ? value : "\"%s\"".formatted(value);
    }
}
