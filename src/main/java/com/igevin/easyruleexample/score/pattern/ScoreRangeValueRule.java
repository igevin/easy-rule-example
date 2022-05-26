package com.igevin.easyruleexample.score.pattern;

import com.igevin.easyruleexample.pattern.ValueHolder;
import com.igevin.easyruleexample.pattern.simple.RangeValueRule;

public class ScoreRangeValueRule extends RangeValueRule<Integer, Integer> {
    private final Integer score;
    public ScoreRangeValueRule(String name, String rangeValue, Integer score) {
        super(name, rangeValue);
        this.score = score;
    }

    @Override
    protected void action(ValueHolder<Integer, Integer> valueHolder) {
        valueHolder.setOutput(score);
    }
}
