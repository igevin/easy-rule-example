package com.igevin.easyruleexample.progress.forewarning;

import lombok.Getter;
import org.jeasy.rules.support.composite.ActivationRuleGroup;
import org.jeasy.rules.support.composite.UnitRuleGroup;

import java.util.List;

public class ForewarningRule {
    @Getter
    private static final String ruleName = "ForeignRule";
    @Getter
    private final ActivationRuleGroup compositeRule;
    public ForewarningRule(List<List<ForewarningConfigurableRule>> metaRuleStruct) {
        compositeRule = new ActivationRuleGroup();
        int groupCount = 0;
        for (List<ForewarningConfigurableRule> metaRuleGroup : metaRuleStruct) {
            UnitRuleGroup unitRuleGroup = new UnitRuleGroup(ruleName + "Group" + (++groupCount));
            for (ForewarningConfigurableRule metaRule : metaRuleGroup) {
                unitRuleGroup.addRule(metaRule.toEasyRule());
            }
            compositeRule.addRule(unitRuleGroup);
        }
    }
}
