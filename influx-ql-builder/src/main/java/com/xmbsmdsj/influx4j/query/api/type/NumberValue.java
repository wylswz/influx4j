package com.xmbsmdsj.influx4j.query.api.type;

import java.math.BigDecimal;

public final record NumberValue (BigDecimal number) implements Value{
    @Override
    public String value() {
        return number.toString();
    }
}
