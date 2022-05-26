package com.igevin.easyruleexample.score;

import com.igevin.easyruleexample.supporting.CompareOperator;
import com.igevin.easyruleexample.supporting.CompareTool;
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
        return CompareTool.compare(operator, value, threshold);
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
