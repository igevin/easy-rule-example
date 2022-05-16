package com.example.easyruleexample.supporting;

import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

public class RuleEngineTool {
    public static RulesEngine createRulesEngine() {
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        return new DefaultRulesEngine(parameters);
    }
}
