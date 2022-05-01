package com.example.easyruleexample.fizzbuzz.rule;


import org.jeasy.rules.support.composite.UnitRuleGroup;

public class FizzBuzz extends UnitRuleGroup {
    public FizzBuzz(Object... rules) {
        for (Object rule : rules) {
            addRule(rule);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
