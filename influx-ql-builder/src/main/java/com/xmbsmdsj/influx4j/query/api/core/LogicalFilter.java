package com.xmbsmdsj.influx4j.query.api.core;

import com.xmbsmdsj.influx4j.query.api.Tokens;
import lombok.RequiredArgsConstructor;

public final class LogicalFilter implements Filter {

  @RequiredArgsConstructor
  enum Logic {
    AND("and"),
    OR("or");

    private final String symbol;

    public String aggregate(String left, String right) {
      return left + " " + symbol + " " + right;
    }
  }

  private Boolean root;
  private Logic logic;
  private Filter left;
  private Filter right;

  public static LogicalFilter create() {
    var res = new LogicalFilter();
    res.root = true;
    return res;
  }

  public LogicalFilter and(Filter left, Filter right) {
    return complete(Logic.AND, left, right);
  }

  public LogicalFilter or(Filter left, Filter right) {
    return complete(Logic.OR, left, right);
  }

  public LogicalFilter complete(Logic logic, Filter left, Filter right) {
    this.logic = logic;
    this.left = left;
    this.right = right;
    return this;
  }

  @Override
  public String materialize() {
    if (!root) {
      throw new IllegalStateException("Non-root logical filter cannot be materialized");
    }
    var sb = new StringBuilder(Tokens.FILTER_PREFIX);
    return buildContent(sb).append(Tokens.FILTER_SUFFIX).toString();
  }

  private StringBuilder buildContent(StringBuilder sb) {
    if (left == null && right == null) {
      return sb.append(Tokens.LITERAL_TRUE);
    } else if (left == null) {
      return sb.append(right.condition());
    } else if (right == null) {
      return sb.append(left.condition());
    } else {
      return sb.append(logic.aggregate(left.condition(), right.condition()));
    }
  }

  @Override
  public String condition() {
    return buildContent(new StringBuilder()).toString();
  }
}
