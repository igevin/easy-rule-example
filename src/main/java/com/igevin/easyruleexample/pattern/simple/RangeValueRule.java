package com.igevin.easyruleexample.pattern.simple;

import com.igevin.easyruleexample.pattern.ValueHolder;
import com.igevin.easyruleexample.supporting.CompareOperator;
import com.igevin.easyruleexample.supporting.CompareTool;
import lombok.Getter;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

/**
 * 区间阈值规则
 *
 * @param <T> 比较字段类型
 * @param <V> 返回结果类型
 */
@Getter
public abstract class RangeValueRule<T extends Comparable<T>, V> {
    private final String name;
    private final String valueRange;
    private CompareOperator leftOperator;
    private CompareOperator rightOperator;
    private T leftValve;
    private T rightValve;

    public RangeValueRule(String name, String valueRange) {
        this.name = name;
        this.valueRange = valueRange;
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
            leftValve = parseValue(valuePair[0].trim());
        } catch (Exception exception) {
            leftValve = null;
        }
        try {
            rightValve = parseValue(valuePair[1].trim());
        } catch (Exception exception) {
            rightValve = null;
        }
    }

    public boolean compare(T value) {
        return CompareTool.compare(leftOperator, value, leftValve)
                && CompareTool.compare(rightOperator, value, rightValve);
    }

    protected T parseValue(String s) throws Exception {
        Class<?> clazz = ((Class<?>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
        Constructor<?> constructor = clazz.getConstructor(String.class);
        Object result = constructor.newInstance(s);
        return (T) result;
    }


    public Rule toEasyRule() {
        return builder().build();
    }

    protected abstract void action(ValueHolder<T, V> valueHolder);

    private RuleBuilder builder() {
        return new RuleBuilder()
                .name(name)
                .description(name)
                .when(facts -> {
                    ValueHolder<T, V> target = (ValueHolder<T, V>) facts.getFact(ValueHolder.getName()).getValue();
                    return compare(target.getInput());
                })
                .then(facts -> {
                    ValueHolder<T, V> target = (ValueHolder<T, V>) facts.getFact(ValueHolder.getName()).getValue();
                    action(target);
                });
    }
}
