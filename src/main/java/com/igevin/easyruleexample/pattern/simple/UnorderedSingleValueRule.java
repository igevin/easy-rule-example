package com.igevin.easyruleexample.pattern.simple;

import com.igevin.easyruleexample.pattern.ValueHolder;
import com.igevin.easyruleexample.supporting.CompareOperator;
import com.igevin.easyruleexample.supporting.CompareTool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

/**
 * 无序阈值规则
 *
 * @param <T> 比较字段类型
 * @param <V> 返回结果类型
 */
@Getter
@AllArgsConstructor
public abstract class UnorderedSingleValueRule<T extends Comparable<T>, V> {
    private final String name;
    private final CompareOperator operator;
    private final T threshold;


    private boolean compare(T value) {
        return CompareTool.compare(operator, value, threshold);
    }

    protected abstract void action(ValueHolder<T, V> valueHolder);


    public Rule toEasyRule() {
        return builder().build();
    }

    protected RuleBuilder builder() {
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
