package com.xmbsmdsj.influx4j.query.api.type;

public record StringValue(String value) implements Value {

    @Override
    public String value() {
        return "\"%s\"".formatted(value);
    }
}
