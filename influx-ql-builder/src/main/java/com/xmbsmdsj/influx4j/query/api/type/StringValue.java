package com.xmbsmdsj.influx4j.query.api.type;

public final record StringValue(String value) implements Value {

    @Override
    public String value() {
        return "\"%s\"".formatted(value);
    }
}
