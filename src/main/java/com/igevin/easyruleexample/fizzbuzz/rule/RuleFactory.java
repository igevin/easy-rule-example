package com.igevin.easyruleexample.fizzbuzz.rule;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.support.composite.UnitRuleGroup;

public class RuleFactory {


    public Rule fizzRule() {
        return new RuleBuilder()
                .name("fizz")
                .description("fizz")
                .priority(1)
                .when(facts -> {
                    Integer number = (Integer) facts.getFact("number").getValue();
                    return number % 5 == 0;
                })
                .then(facts -> {
                    Integer number = (Integer) facts.getFact("number").getValue();
                    System.out.printf("fizz(%s)", number);
                }).build();
    }

    public Rule buzzRule() {
        return new RuleBuilder()
                .name("buzz")
                .description("buzz")
                .priority(2)
                .when(facts -> {
                    Integer number = (Integer) facts.getFact("number").getValue();
                    return number % 7 == 0;
                })
                .then(facts -> {
                    Integer number = (Integer) facts.getFact("number").getValue();
                    System.out.printf("buzz(%s)", number);
                }).build();
    }

    public Rule nonFizzBuzzRule() {
        return new RuleBuilder()
                .name("nonFizzBuzz")
                .description("nonFizzBuzz")
                .priority(3)
                .when(facts -> {
                    Integer number = (Integer) facts.getFact("number").getValue();
                    return number % 5 != 0 || number % 7 != 0;
                })
                .then(facts -> {
                    Integer number = (Integer) facts.getFact("number").getValue();
                    System.out.print(number);
                }).build();
    }

    public Rule fizzBuzzRule() {
        Rule fizzRule = fizzRule();
        Rule buzzRule = buzzRule();
        return fizzBuzzRule(fizzRule, buzzRule);
    }

    public Rule fizzBuzzRule(Rule fizzRule, Rule buzzRule) {
        UnitRuleGroup fizzBuzz = new UnitRuleGroup("fizzbuzz", "fizzbuzz", 0);
        fizzBuzz.addRule(fizzRule);
        fizzBuzz.addRule(buzzRule);
        return fizzBuzz;
    }
}
