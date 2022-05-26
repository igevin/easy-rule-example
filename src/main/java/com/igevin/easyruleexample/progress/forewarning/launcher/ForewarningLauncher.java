package com.igevin.easyruleexample.progress.forewarning.launcher;

import com.igevin.easyruleexample.progress.forewarning.ForewarningConfigurableRule;
import com.igevin.easyruleexample.progress.forewarning.ForewarningRule;
import com.igevin.easyruleexample.progress.forewarning.ForewarningTask;
import com.igevin.easyruleexample.supporting.CompareOperator;
import com.igevin.easyruleexample.supporting.RuleEngineTool;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;

public class ForewarningLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ForewarningLauncher.class, args);

        // create engine
        RulesEngine rulesEngine = RuleEngineTool.createRulesEngine();
        Rules rules = registerRules();
        Facts facts = new Facts();
        for (ForewarningTask task : LauncherHelper.createTasks()) {
            facts.put(ForewarningTask.getName(), task);
            rulesEngine.fire(rules, facts);
            System.out.println(task.getId() + ", status: " + task.isForewarning() + " --> " + task);
        }

    }

    private static List<List<ForewarningConfigurableRule>> createRules() {
        List<List<ForewarningConfigurableRule>> rules = new ArrayList<>();

        List<ForewarningConfigurableRule> ruleGroup1 = new ArrayList<>();
        ruleGroup1.add(new ForewarningConfigurableRule("completionRate", CompareOperator.LESS_THAN, 90d));
        ruleGroup1.add(new ForewarningConfigurableRule("plannedRemainingDays", CompareOperator.LESS_THAN, 2d));
        rules.add(ruleGroup1);

        List<ForewarningConfigurableRule> ruleGroup2 = new ArrayList<>();
        ruleGroup2.add(new ForewarningConfigurableRule("estimatedDelayDays", CompareOperator.GREATER_THAN_OR_EQUAL_TO, 3d));
        rules.add(ruleGroup2);

        List<ForewarningConfigurableRule> ruleGroup3 = new ArrayList<>();
        ruleGroup3.add(new ForewarningConfigurableRule("plannedRemainingDays", CompareOperator.LESS_THAN_OR_EQUAL_TO, 0d));
        rules.add(ruleGroup3);


        return rules;
    }

    private static Rules registerRules() {
        ForewarningRule forewarningRule = new ForewarningRule(createRules());
        return new Rules(forewarningRule.getCompositeRule());
    }

}
