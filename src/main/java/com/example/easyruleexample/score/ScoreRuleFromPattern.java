package com.example.easyruleexample.score;

import com.example.easyruleexample.pattern.simple.example.ScoreValueRule;
import com.example.easyruleexample.supporting.CompareOperator;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScoreRuleFromPattern {
    public static void main(String[] args) {
        SpringApplication.run(ScoreRuleFromPattern.class, args);

        // create engine
        RulesEngine rulesEngine = ScoreLauncherHelper.createRulesEngine();

        // create rules
        Rules rules = registerEasyRules(createRules());

        // fire rules
        Facts facts = new Facts();
        ScoreLauncherHelper.validateFactsFromPattern(facts, rulesEngine, rules);

    }

    private static List<ScoreValueRule> createRules() {
        List<ScoreValueRule> rules = new ArrayList<>(10);
        ScoreValueRule rule = new ScoreValueRule("rule1", CompareOperator.GREATER_THAN_OR_EQUAL_TO, 100, 5);
        rules.add(rule);
        rule = new ScoreValueRule("rule2", CompareOperator.GREATER_THAN, 80, 4);
        rules.add(rule);
        rule = new ScoreValueRule("rule3", CompareOperator.GREATER_THAN, 60, 3);
        rules.add(rule);
        rule = new ScoreValueRule("rule4", CompareOperator.GREATER_THAN, 40, 2);
        rules.add(rule);
        rule = new ScoreValueRule("rule4", CompareOperator.LESS_THAN_OR_EQUAL_TO, 40, 1);
        rules.add(rule);
        return rules;
    }

    private static Rules registerEasyRules(List<ScoreValueRule> dynamicIntegerRules) {
        Rules rules = new Rules();
        for (int i = 0; i < dynamicIntegerRules.size(); i++) {
            rules.register(dynamicIntegerRules.get(i).toEasyRule(i + 100));
        }
        return rules;
    }
}
