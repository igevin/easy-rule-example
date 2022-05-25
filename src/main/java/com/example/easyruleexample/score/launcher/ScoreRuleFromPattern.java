package com.example.easyruleexample.score.launcher;

import com.example.easyruleexample.score.ScoreValueRule;
import com.example.easyruleexample.score.launcher.helper.ScoreLauncherHelper;
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
        ScoreValueRule rule = new ScoreValueRule("rule1", CompareOperator.GREATER_THAN_OR_EQUAL_TO, 100, 101, 5);
        rules.add(rule);
        rule = new ScoreValueRule("rule2", CompareOperator.GREATER_THAN, 80, 102, 4);
        rules.add(rule);
        rule = new ScoreValueRule("rule3", CompareOperator.GREATER_THAN, 60, 103, 3);
        rules.add(rule);
        rule = new ScoreValueRule("rule4", CompareOperator.GREATER_THAN, 40, 104, 2);
        rules.add(rule);
        rule = new ScoreValueRule("rule4", CompareOperator.LESS_THAN_OR_EQUAL_TO, 40, 105, 1);
        rules.add(rule);
        return rules;
    }

    private static Rules registerEasyRules(List<ScoreValueRule> dynamicIntegerRules) {
        Rules rules = new Rules();
        for (int i = 0; i < dynamicIntegerRules.size(); i++) {
            rules.register(dynamicIntegerRules.get(i).toEasyRule(i + 100));
//            rules.register(dynamicIntegerRules.get(i).toEasyRule());
        }
        return rules;
    }
}
