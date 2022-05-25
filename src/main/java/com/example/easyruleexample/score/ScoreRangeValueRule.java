package com.example.easyruleexample.score;

import com.example.easyruleexample.pattern.DataHolder;
import com.example.easyruleexample.pattern.simple.RangeValueRule;

public class ScoreRangeValueRule extends RangeValueRule<Integer, Integer> {
    private final Integer score;
    public ScoreRangeValueRule(String name, String rangeValue, Integer score) {
        super(name, rangeValue);
        this.score = score;
    }

    @Override
    protected void action(DataHolder<Integer, Integer> dataHolder) {
        dataHolder.setOutput(score);
    }
}
