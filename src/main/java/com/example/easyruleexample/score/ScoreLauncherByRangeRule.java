package com.example.easyruleexample.score;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScoreLauncherByRangeRule {
    public static void main(String[] args) {
        SpringApplication.run(ScoreLauncherByRangeRule.class, args);

        // create engine
        RulesEngine rulesEngine = createRulesEngine();

        // create rules
        Rules rules = registerEasyRules(createRules());

        // fire rules
        Facts facts = new Facts();
        validateFacts(facts, rulesEngine, rules);
        validateCustomFacts(facts, rulesEngine, rules);


    }

    private static List<DynamicIntegerRangeRule> createRules() {
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

    private static RulesEngine createRulesEngine() {
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        return new DefaultRulesEngine(parameters);
    }

    private static Rules registerEasyRules(List<DynamicIntegerRangeRule> dynamicIntegerRangeRules) {
        Rules rules = new Rules();
        for (DynamicIntegerRangeRule rule : dynamicIntegerRangeRules) {
            rules.register(rule.toEasyRule());
        }
        return rules;
    }

    private static void validateFacts(Facts facts, RulesEngine rulesEngine, Rules rules) {
        for (int i = 1; i <= 10; i++) {
            RuleTarget target = new RuleTarget(i * 10);
            facts.put(RuleTarget.getName(), target);
            rulesEngine.fire(rules, facts);
            System.out.println("value: " + target.getValue() + ", score: " + target.getScore());
        }
    }

    private static void validateCustomFacts(Facts facts, RulesEngine rulesEngine, Rules rules) {
        RuleTarget target = new RuleTarget(91);
        facts.put(RuleTarget.getName(), target);
        rulesEngine.fire(rules, facts);
        System.out.println("value: " + target.getValue() + ", score: " + target.getScore());

        target = new RuleTarget(12);
        facts.put(RuleTarget.getName(), target);
        rulesEngine.fire(rules, facts);
        System.out.println("value: " + target.getValue() + ", score: " + target.getScore());

        target = new RuleTarget(10000);
        facts.put(RuleTarget.getName(), target);
        rulesEngine.fire(rules, facts);
        System.out.println("value: " + target.getValue() + ", score: " + target.getScore());
    }
}
