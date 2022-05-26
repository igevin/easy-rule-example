package com.igevin.easyruleexample.score.launcher;

import com.igevin.easyruleexample.score.DynamicIntegerRangeRule;
import com.igevin.easyruleexample.score.launcher.helper.ScoreLauncherHelper;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScoreByRangeRule {
    public static void main(String[] args) {
        SpringApplication.run(ScoreByRangeRule.class, args);

        // create engine
        RulesEngine rulesEngine = ScoreLauncherHelper.createRulesEngine();

        // create rules
        Rules rules = registerEasyRules(createRules());

        // fire rules
        Facts facts = new Facts();
        ScoreLauncherHelper.validateFacts(facts, rulesEngine, rules);
        ScoreLauncherHelper.validateCustomFacts(facts, rulesEngine, rules);


    }

    public static List<DynamicIntegerRangeRule> createRules() {
        List<DynamicIntegerRangeRule> rules = new ArrayList<>(10);
        DynamicIntegerRangeRule rule = new DynamicIntegerRangeRule("rule1", "[100, infinity)", 5);
        rules.add(rule);
        rule = new DynamicIntegerRangeRule("rule2", "[80, 100)", 4);
        rules.add(rule);
        rule = new DynamicIntegerRangeRule("rule3", "[60, 80)", 3);
        rules.add(rule);
        rule = new DynamicIntegerRangeRule("rule4", "[40, 60)", 2);
        rules.add(rule);
        rule = new DynamicIntegerRangeRule("rule5", "[-100, 40)", 1);
        rules.add(rule);

        return rules;
    }


    private static Rules registerEasyRules(List<DynamicIntegerRangeRule> dynamicIntegerRangeRules) {
        Rules rules = new Rules();
        for (DynamicIntegerRangeRule rule : dynamicIntegerRangeRules) {
            rules.register(rule.toEasyRule());
        }
        return rules;
    }

}
