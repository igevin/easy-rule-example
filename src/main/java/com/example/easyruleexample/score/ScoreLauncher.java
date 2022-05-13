package com.example.easyruleexample.score;

import com.example.easyruleexample.fizzbuzz.rule.Buzz;
import com.example.easyruleexample.fizzbuzz.rule.Fizz;
import com.example.easyruleexample.fizzbuzz.rule.FizzBuzz;
import com.example.easyruleexample.fizzbuzz.rule.NonFizzBuzz;
import com.example.easyruleexample.supporting.CompareOperator;
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
public class ScoreLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ScoreLauncher.class, args);
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);

        // create rules
        Rules rules = new Rules();
        List<DynamicIntegerRule> dynamicIntegerRules = createRules();
        for (int i = 0; i < dynamicIntegerRules.size(); i++) {
            rules.register(dynamicIntegerRules.get(i).toEasyRule(i + 100));
        }

        // fire rules
        Facts facts = new Facts();
        for (int i = 1; i <= 10; i++) {
            RuleTarget target = new RuleTarget(i * 10);
            facts.put(RuleTarget.getName(), target);
            rulesEngine.fire(rules, facts);
            System.out.println("value: " + target.getValue() + ", score: " + target.getScore());
        }

        RuleTarget target = new RuleTarget(91);
        facts.put(RuleTarget.getName(), target);
        rulesEngine.fire(rules, facts);
        System.out.println("value: " + target.getValue() + ", score: " + target.getScore());

        target = new RuleTarget(12);
        facts.put(RuleTarget.getName(), target);
        rulesEngine.fire(rules, facts);
        System.out.println("value: " + target.getValue() + ", score: " + target.getScore());
    }

    private static List<DynamicIntegerRule> createRules() {
        List<DynamicIntegerRule> rules = new ArrayList<>(10);
        DynamicIntegerRule rule = new DynamicIntegerRule("rule1", CompareOperator.GREATER_THAN_OR_EQUAL_TO, 100, 5);
        rules.add(rule);
        rule = new DynamicIntegerRule("rule2", CompareOperator.GREATER_THAN, 80, 4);
        rules.add(rule);
        rule = new DynamicIntegerRule("rule3", CompareOperator.GREATER_THAN, 60, 3);
        rules.add(rule);
        rule = new DynamicIntegerRule("rule4", CompareOperator.GREATER_THAN, 40, 2);
        rules.add(rule);
        rule = new DynamicIntegerRule("rule4", CompareOperator.LESS_THAN_OR_EQUAL_TO, 40, 1);
        rules.add(rule);
        return rules;
    }
}
