package com.xmbsmdsj.influx4j.query.api.type;

import com.xmbsmdsj.influx4j.query.api.Tokens;

public final record BooleanValue(Boolean b) implements Value {

    public String value() {
        return b ? Tokens.LITERAL_TRUE : Tokens.LITERAL_FALSE;
    }
}
