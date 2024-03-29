package com.igevin.easyruleexample.pattern.composite;

import lombok.Getter;
import org.jeasy.rules.support.composite.ActivationRuleGroup;
import org.jeasy.rules.support.composite.UnitRuleGroup;

import java.util.List;

/**
 * 两层分组规则
 *
 * @param <E> 原子规则类型
 */
public class TwoLayerGroupedRule<E extends MetaRule>{
    @Getter
    private static final String ruleName = "TwoLayerGroupedRule";
    @Getter
    private final ActivationRuleGroup compositeRule;
    public TwoLayerGroupedRule(List<List<E>> metaRuleStruct) {
        compositeRule = new ActivationRuleGroup();
        int groupCount = 0;
        for (List<E> metaRuleGroup : metaRuleStruct) {
            UnitRuleGroup unitRuleGroup = new UnitRuleGroup(ruleName + "Group" + (++groupCount));
            for (E metaRule : metaRuleGroup) {
                unitRuleGroup.addRule(metaRule.toEasyRule());
            }
            compositeRule.addRule(unitRuleGroup);
        }
    }


}
