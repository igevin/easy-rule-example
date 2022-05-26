package com.igevin.easyruleexample.progress.forewarning.pattern;

import com.igevin.easyruleexample.pattern.ObjectHolder;
import com.igevin.easyruleexample.pattern.composite.MetaRule;
import com.igevin.easyruleexample.progress.forewarning.ForewarningRule;
import com.igevin.easyruleexample.supporting.CompareOperator;

public class ForewarningMetaRule extends MetaRule<Double, ForewarningRule, Boolean> {
    public ForewarningMetaRule(String field, CompareOperator operator, Double threshold) {
        super(field, operator, threshold);
    }

    @Override
    protected void action(ObjectHolder<ForewarningRule, Boolean> objectHolder) {
        objectHolder.setOutput(true);
    }
}
