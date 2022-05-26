package com.igevin.easyruleexample.score;

import com.igevin.easyruleexample.pattern.DataHolder;
import com.igevin.easyruleexample.pattern.simple.OrderedSingleValueRule;
import com.igevin.easyruleexample.supporting.CompareOperator;

public class ScoreValueRule extends OrderedSingleValueRule<Integer, Integer> {
    private final Integer score;
    public ScoreValueRule(String name, CompareOperator operator, Integer threshold, Integer priority, Integer score) {
        super(name, operator, threshold, priority);
        this.score = score;
    }

    @Override
    protected void action(DataHolder<Integer, Integer> dataHolder) {
        dataHolder.setOutput(score);
    }
}
