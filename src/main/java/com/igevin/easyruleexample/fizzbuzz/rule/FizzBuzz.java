package com.igevin.easyruleexample.fizzbuzz.rule;


import org.jeasy.rules.api.Facts;
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

    @Override
    public void execute(Facts facts) throws Exception {
        Integer number = facts.get("number");
        System.out.printf("fizzbuzz(%s)", number);

    }
}
