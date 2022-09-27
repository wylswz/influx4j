package com.xmbsmdsj.influx4j.query.internal;

import com.xmbsmdsj.influx4j.query.api.Tokens;
import com.xmbsmdsj.influx4j.query.api.utils.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface IFunction {
    String name();
    List<IArgs> arguments();

    default String materializeFunction() {
        var sb = new StringBuilder();
        sb.append(name())
                .append(Tokens.FUNC_ARG_LIST_START);
        int i=0;
        for (IArgs arg : arguments()) {
            sb.append(
                    StringUtils.passParam(
                            arg.name(), arg.value(), i>0
                    )
            );
            i++;
        }
        sb.append(Tokens.FUNC_ARG_LIST_END);
        return sb.toString();
    }
}
