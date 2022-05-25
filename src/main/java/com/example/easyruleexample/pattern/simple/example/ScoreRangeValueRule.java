package com.example.easyruleexample.pattern.simple.example;

import com.example.easyruleexample.pattern.DataHolder;
import com.example.easyruleexample.pattern.simple.RangeValueRule;
import com.example.easyruleexample.pattern.simple.SerialSingleValueRule;
import com.example.easyruleexample.supporting.CompareOperator;

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
