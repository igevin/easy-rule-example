package com.example.easyruleexample.pattern.simple;

import com.example.easyruleexample.pattern.DataHolder;
import com.example.easyruleexample.supporting.CompareOperator;
import com.example.easyruleexample.supporting.CompareTool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

@Getter
@AllArgsConstructor
public abstract class UnorderedSingleValueRule<T extends Comparable<T>, V> {
    private final String name;
    private final CompareOperator operator;
    private final T threshold;


    private boolean compare(T value) {
        return CompareTool.compare(operator, value, threshold);
    }

    protected abstract void action(DataHolder<T, V> dataHolder);


    public Rule toEasyRule() {
        return builder().build();
    }

    protected RuleBuilder builder() {
        return new RuleBuilder()
                .name(name)
                .description(name)
                .when(facts -> {
                    DataHolder<T, V> target = (DataHolder<T, V>) facts.getFact(DataHolder.getName()).getValue();
                    return compare(target.getInput());
                })
                .then(facts -> {
                    DataHolder<T, V> target = (DataHolder<T, V>) facts.getFact(DataHolder.getName()).getValue();
                    action(target);
                });
    }
}
