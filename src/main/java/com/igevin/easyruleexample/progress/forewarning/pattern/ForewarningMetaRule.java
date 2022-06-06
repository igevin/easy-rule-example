package com.igevin.easyruleexample.progress.forewarning.pattern;

import com.igevin.easyruleexample.pattern.ObjectHolder;
import com.igevin.easyruleexample.pattern.composite.MetaRule;
import com.igevin.easyruleexample.progress.forewarning.ForewarningTask;
import com.igevin.easyruleexample.supporting.CompareOperator;

public class ForewarningMetaRule extends MetaRule<Double, ForewarningTask, Boolean> {
    public ForewarningMetaRule(String field, CompareOperator operator, Double threshold) {
        super(field, operator, threshold);
    }

    @Override
    protected void action(ObjectHolder<ForewarningTask, Boolean> objectHolder) {
        objectHolder.setOutput(true);
    }
}
