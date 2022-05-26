package com.igevin.easyruleexample.pattern.simple;

import com.igevin.easyruleexample.pattern.DataHolder;
import com.igevin.easyruleexample.supporting.CompareOperator;
import lombok.Getter;
import org.jeasy.rules.api.Rule;

@Getter
public abstract class OrderedSingleValueRule<T extends Comparable<T>, V> extends UnorderedSingleValueRule<T, V>{
    private int priority;
    public OrderedSingleValueRule(String name, CompareOperator operator, T threshold, int priority) {
        super(name, operator, threshold);
        this.priority = priority;
    }

    protected abstract void action(DataHolder<T, V> dataHolder);

    public Rule toEasyRule() {
        return builder().priority(priority).build();
    }

    public Rule toEasyRule(int priority) {
        this.priority = priority;
        return toEasyRule();
    }
}
