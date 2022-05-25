package com.example.easyruleexample.score;

import com.example.easyruleexample.pattern.simple.example.ScoreRangeValueRule;
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
public class ScoreRangeRuleFromPattern {
    public static void main(String[] args) {
        SpringApplication.run(ScoreRangeRuleFromPattern.class, args);

        // create engine
        RulesEngine rulesEngine = ScoreLauncherHelper.createRulesEngine();

        // create rules
        Rules rules = registerEasyRules(createRules());

        // fire rules
        Facts facts = new Facts();
        ScoreLauncherHelper.validateFactsFromPattern(facts, rulesEngine, rules);

    }

    private static List<ScoreRangeValueRule> createRules() {
        List<ScoreRangeValueRule> rules = new ArrayList<>(10);
        ScoreRangeValueRule rule = new ScoreRangeValueRule("rule1", "[100, 10000)", 5);
        rules.add(rule);
        rule = new ScoreRangeValueRule("rule2", "[80, 100)", 4);
        rules.add(rule);
        rule = new ScoreRangeValueRule("rule3", "[60, 80)", 3);
        rules.add(rule);
        rule = new ScoreRangeValueRule("rule4", "[40, 60)", 2);
        rules.add(rule);
        rule = new ScoreRangeValueRule("rule5", "[-100, 40)", 1);
        rules.add(rule);
        return rules;
    }

    private static Rules registerEasyRules(List<ScoreRangeValueRule> dynamicIntegerRules) {
        Rules rules = new Rules();
        for (ScoreRangeValueRule dynamicIntegerRule : dynamicIntegerRules) {
            rules.register(dynamicIntegerRule.toEasyRule());
        }
        return rules;
    }
}
