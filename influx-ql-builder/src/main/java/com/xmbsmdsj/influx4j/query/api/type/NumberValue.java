package com.xmbsmdsj.influx4j.query.api.type;

import java.math.BigDecimal;

public record NumberValue (BigDecimal number) implements Value{
    @Override
    public String value() {
        return number.toString();
    }
}
