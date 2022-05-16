package com.example.easyruleexample.progress.forewarning;

import com.example.easyruleexample.supporting.CompareOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public class ForewarningConfigurableRule {
    private String field;
    private CompareOperator operator;
    private Double threshold;

    public Rule toEasyRule() {
        return createRule(this);
    }

    public boolean compare(double value) {
        switch (operator) {
            case GREATER_THAN: return threshold.compareTo(value) < 0;
            case GREATER_THAN_OR_EQUAL_TO: return threshold.compareTo(value) <= 0;
            case LESS_THAN: return threshold.compareTo(value) > 0;
            case LESS_THAN_OR_EQUAL_TO: return threshold.compareTo(value) >= 0;
            default: return threshold.compareTo(value) == 0;
        }
    }

    @SneakyThrows
    private Double fetchValueFromTask(String fieldValue, ForewarningTask task) {
        Field[] fields = ForewarningTask.class.getDeclaredFields();
        Field field = Arrays.stream(fields).filter(x -> x.getName().equals(fieldValue)).findAny().orElse(null);
        if (field == null) {
            throw new RuntimeException(fieldValue + " field not found");
        }
        field.setAccessible(true);
        return (Double)field.get(task);
    }

    private Rule createRule(ForewarningConfigurableRule metaRule) {
        return new RuleBuilder()
                .name(metaRule.field)
                .description(metaRule.field)
                .when(facts -> {
                    ForewarningTask task = (ForewarningTask) facts.getFact(ForewarningTask.getName()).getValue();
                    return compare(fetchValueFromTask(metaRule.field, task));
                })
                .then(facts -> {
                    ForewarningTask task = (ForewarningTask) facts.getFact(ForewarningTask.getName()).getValue();
                    task.setForewarning(true);
                })
                .build();
    }
}
