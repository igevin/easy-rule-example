package com.igevin.easyruleexample.pattern.simple;

import com.igevin.easyruleexample.pattern.ValueHolder;
import com.igevin.easyruleexample.supporting.CompareOperator;
import lombok.Getter;
import org.jeasy.rules.api.Rule;

/**
 * 有序阈值规则
 *
 * @param <T> 比较字段类型
 * @param <V> 返回结果类型
 */
@Getter
public abstract class OrderedSingleValueRule<T extends Comparable<T>, V> extends UnorderedSingleValueRule<T, V>{
    private int priority;
    public OrderedSingleValueRule(String name, CompareOperator operator, T threshold, int priority) {
        super(name, operator, threshold);
        this.priority = priority;
    }

    protected abstract void action(ValueHolder<T, V> valueHolder);

    public Rule toEasyRule() {
        return builder().priority(priority).build();
    }

    public Rule toEasyRule(int priority) {
        this.priority = priority;
        return toEasyRule();
    }
}
