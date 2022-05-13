package com.example.easyruleexample.score;

import com.example.easyruleexample.supporting.CompareOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

@Getter
@AllArgsConstructor
public class DynamicIntegerRule {
    private final String name;
    private final CompareOperator operator;
    private final Integer threshold;
    private final Integer score;


    public boolean compare(Integer value) {
        switch (operator) {
            case GREATER_THAN: return threshold.compareTo(value) < 0;
            case GREATER_THAN_OR_EQUAL_TO: return threshold.compareTo(value) <= 0;
            case LESS_THAN: return threshold.compareTo(value) > 0;
            case LESS_THAN_OR_EQUAL_TO: return threshold.compareTo(value) >= 0;
            default: return threshold.compareTo(value) == 0;
        }
    }


    public Rule toEasyRule() {
        return builder().build();
    }

    public Rule toEasyRule(int priority) {
        return builder().priority(priority).build();
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
