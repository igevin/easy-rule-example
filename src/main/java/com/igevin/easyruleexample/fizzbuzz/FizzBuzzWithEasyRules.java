package com.igevin.easyruleexample.fizzbuzz;

import com.igevin.easyruleexample.fizzbuzz.rule.Buzz;
import com.igevin.easyruleexample.fizzbuzz.rule.Fizz;
import com.igevin.easyruleexample.fizzbuzz.rule.FizzBuzz;
import com.igevin.easyruleexample.fizzbuzz.rule.NonFizzBuzz;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FizzBuzzWithEasyRules {
    public static void main(String[] args) {
        SpringApplication.run(FizzBuzzWithEasyRules.class, args);
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);

        // create rules
        Rules rules = new Rules();
        rules.register(new Fizz());
        rules.register(new Buzz());
        rules.register(new FizzBuzz(new Fizz(), new Buzz()));
        rules.register(new NonFizzBuzz());

        // fire rules
        Facts facts = new Facts();
        for (int i = 1; i <= 100; i++) {
            facts.put("number", i);
            fizzBuzzEngine.fire(rules, facts);
            System.out.println();
        }
    }
}
