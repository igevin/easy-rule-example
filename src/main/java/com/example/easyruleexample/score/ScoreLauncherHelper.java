package com.example.easyruleexample.score;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

public class ScoreLauncherHelper {
    public static RulesEngine createRulesEngine() {
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        return new DefaultRulesEngine(parameters);
    }

    public static void validateFacts(Facts facts, RulesEngine rulesEngine, Rules rules) {
        for (int i = 1; i <= 10; i++) {
            RuleTarget target = new RuleTarget(i * 10);
            facts.put(RuleTarget.getName(), target);
            rulesEngine.fire(rules, facts);
            System.out.println("value: " + target.getValue() + ", score: " + target.getScore());
        }
    }

    public static void validateCustomFacts(Facts facts, RulesEngine rulesEngine, Rules rules) {
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
