package com.igevin.easyruleexample.fizzbuzz;

import com.igevin.easyruleexample.fizzbuzz.rule.*;
import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FizzBuzzWithEasyRules {
    public static void main(String[] args) {
        SpringApplication.run(FizzBuzzWithEasyRules.class, args);
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);

        Rules rules = createRules();
//        Rules rules = createRulesFromFactory();
//        Rules rules = createRulesFromFactory2();

        fireRules(fizzBuzzEngine, rules);
    }

    private static void fireRules(RulesEngine engine, Rules rules) {
        Facts facts = new Facts();
        for (int i = 1; i <= 100; i++) {
            facts.put("number", i);
            engine.fire(rules, facts);
            System.out.println();
        }
    }

    private static Rules createRules() {
        Rules rules = new Rules();
        rules.register(new Fizz());
        rules.register(new Buzz());
        rules.register(new FizzBuzz(new Fizz(), new Buzz()));
        rules.register(new NonFizzBuzz());
        return rules;
    }

    private static Rules createRulesFromFactory() {
        Rules rules = new Rules();
        RuleFactory factory = new RuleFactory();
        Rule fizz = factory.fizzRule();
        Rule buzz = factory.buzzRule();
        rules.register(fizz);
        rules.register(buzz);
        rules.register(factory.fizzBuzzRule(fizz, buzz));
        rules.register(factory.nonFizzBuzzRule());
        return rules;
    }
    private static Rules createRulesFromFactory2() {
        Rules rules = new Rules();
        RuleFactory2 factory = new RuleFactory2();
        rules.register(factory.fizzRule());
        rules.register(factory.buzzRule());
        rules.register(factory.fizzBuzzRule());
        rules.register(factory.nonFizzBuzzRule());
        return rules;
    }
}
