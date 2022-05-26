package com.igevin.easyruleexample.score;

import com.igevin.easyruleexample.supporting.CompareOperator;
import com.igevin.easyruleexample.supporting.CompareTool;
import lombok.Getter;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

@Getter
public class DynamicIntegerRangeRule {
    private final String name;
    private final String valueRange;
    private CompareOperator leftOperator;
    private CompareOperator rightOperator;
    private Integer leftValve;
    private Integer rightValve;
    private final Integer score;

    public DynamicIntegerRangeRule(String name, String valueRange, Integer score) {
        this.name = name;
        this.valueRange = valueRange;
        this.score = score;
        parseValueRange();
    }

    private void parseValueRange() {
        if (!validateValueRangeAndParseOperator(valueRange)) {
            throw new RuntimeException("value range is not valid");
        }
        parseValve(valueRange);
    }

    private boolean validateValueRangeAndParseOperator(String valueRange) {
        if (valueRange.length() < 5) {
            return false;
        }
        char leftSymbol = valueRange.charAt(0);
        char rightSymbol = valueRange.charAt(valueRange.length() - 1);
        if (!leftSymbolValid(leftSymbol) || !rightSymbolValid(rightSymbol)) {
            return false;
        }
        if (valueRange.indexOf(",") != valueRange.lastIndexOf(",")) {
            return false;
        }
        leftOperator = parseLeftOperator(leftSymbol);
        rightOperator = parseRightOperator(rightSymbol);
        return true;
    }

    private CompareOperator parseLeftOperator(char symbol) {
        if (symbol == '(') {
            return CompareOperator.GREATER_THAN;
        }
        return CompareOperator.GREATER_THAN_OR_EQUAL_TO;
    }

    private CompareOperator parseRightOperator(char symbol) {
        if (symbol == ')') {
            return CompareOperator.LESS_THAN;
        }
        return CompareOperator.LESS_THAN_OR_EQUAL_TO;
    }

    private boolean leftSymbolValid(char symbol) {
        return symbol == '(' || symbol == '[';
    }

    private boolean rightSymbolValid(char symbol) {
        return symbol == ')' || symbol == ']';
    }

    private void parseValve(String valueRange) {
        String range = valueRange.substring(1, valueRange.length() - 1);
        String[] valuePair = range.split(",");
        try {
            leftValve = Integer.parseInt(valuePair[0].trim());
        } catch (NumberFormatException exception) {
            leftValve = Integer.MIN_VALUE;
        }
        try {
            rightValve = Integer.parseInt(valuePair[1].trim());
        } catch (NumberFormatException exception) {
            rightValve = Integer.MAX_VALUE;
        }
    }

    public boolean compare(Integer value) {
        return CompareTool.compare(leftOperator, value, leftValve)
                && CompareTool.compare(rightOperator, value, rightValve);
    }


    public Rule toEasyRule() {
        return builder().build();
    }

    private RuleBuilder builder() {
        return new RuleBuilder()
                .name(name)
                .description(name)
                .when(facts -> {
                    RuleTarget target = (RuleTarget) facts.getFact(RuleTarget.getName()).getValue();
                    return compare(target.getValue());
                })
                .then(facts -> {
                    RuleTarget target = (RuleTarget) facts.getFact(RuleTarget.getName()).getValue();
                    target.setScore(score);
                });
    }
}
