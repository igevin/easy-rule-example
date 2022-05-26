package com.igevin.easyruleexample.pattern.composite;

import com.igevin.easyruleexample.pattern.ObjectHolder;
import com.igevin.easyruleexample.supporting.CompareOperator;
import com.igevin.easyruleexample.supporting.CompareTool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

/**
 * 复合规则中的原子规则
 *
 * @param <T> 比较字段的类型
 * @param <K> 目标对象
 * @param <V> 返回结果类型
 */
@Getter
@AllArgsConstructor
public abstract class MetaRule<T extends Comparable<T>, K, V> {
    private String field;
    private CompareOperator operator;
    private T threshold;

    public Rule toEasyRule() {
        return createRule(this);
    }

    public boolean compare(T value) {
        return CompareTool.compare(operator, value, threshold);
    }

    protected abstract void action(ObjectHolder<K, V> objectHolder);

    @SneakyThrows
    private T fetchValueFromTarget(String fieldValue, Object target) {
        Field[] fields = target.getClass().getDeclaredFields();
        Optional<Field> optionalField = Arrays.stream(fields).filter(x -> x.getName().equals(fieldValue)).findAny();
        if (!optionalField.isPresent()) {
            throw new RuntimeException(fieldValue + " field not found");
        }
        Field field = optionalField.get();
        field.setAccessible(true);
        return (T) field.get(target);
    }

    private Rule createRule(MetaRule<T, K, V> metaRule) {
        return new RuleBuilder()
                .name(metaRule.field)
                .description(metaRule.field)
                .when(facts -> {
                    ObjectHolder<K, V> target = (ObjectHolder<K, V>) facts.getFact(ObjectHolder.getName()).getValue();
                    return compare(fetchValueFromTarget(metaRule.field, target.getInput()));
                })
                .then(facts -> {
                    ObjectHolder<K, V> target = (ObjectHolder<K, V>) facts.getFact(ObjectHolder.getName()).getValue();
                    action(target);
                })
                .build();
    }
}
