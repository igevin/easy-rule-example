package com.igevin.easyruleexample.progress.forewarning.launcher;

import com.igevin.easyruleexample.pattern.ObjectHolder;
import com.igevin.easyruleexample.pattern.composite.TwoLayerGroupedRule;
import com.igevin.easyruleexample.progress.forewarning.ForewarningTask;
import com.igevin.easyruleexample.progress.forewarning.pattern.ForewarningMetaRule;
import com.igevin.easyruleexample.supporting.CompareOperator;
import com.igevin.easyruleexample.supporting.RuleEngineTool;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class ForewarningPatternLauncher {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(ForewarningPatternLauncher.class, args)) {
            RulesEngine rulesEngine = RuleEngineTool.createRulesEngine();
            Rules rules = registerRules();
            Facts facts = new Facts();
            for (ForewarningTask task : LauncherHelper.createTasks()) {
                ObjectHolder<ForewarningTask, Boolean> objectHolder = new ObjectHolder<>(task);
                facts.put(ObjectHolder.getName(), objectHolder);
                rulesEngine.fire(rules, facts);
                System.out.println(task.getId() + ", status: " + objectHolder.getOutput() + " --> " + task);
            }
        }

    }

    private static List<List<ForewarningMetaRule>> createRules() {
        List<List<ForewarningMetaRule>> rules = new ArrayList<>();

        List<ForewarningMetaRule> ruleGroup1 = new ArrayList<>();
        ruleGroup1.add(new ForewarningMetaRule("completionRate", CompareOperator.LESS_THAN, 90d));
        ruleGroup1.add(new ForewarningMetaRule("plannedRemainingDays", CompareOperator.LESS_THAN, 2d));
        rules.add(ruleGroup1);

        List<ForewarningMetaRule> ruleGroup2 = new ArrayList<>();
        ruleGroup2.add(new ForewarningMetaRule("estimatedDelayDays", CompareOperator.GREATER_THAN_OR_EQUAL_TO, 3d));
        rules.add(ruleGroup2);

        List<ForewarningMetaRule> ruleGroup3 = new ArrayList<>();
        ruleGroup3.add(new ForewarningMetaRule("plannedRemainingDays", CompareOperator.LESS_THAN_OR_EQUAL_TO, 0d));
        rules.add(ruleGroup3);


        return rules;
    }

    private static Rules registerRules() {
        TwoLayerGroupedRule<ForewarningMetaRule> forewarningRule = new TwoLayerGroupedRule(createRules());
        return new Rules(forewarningRule.getCompositeRule());
    }

}
