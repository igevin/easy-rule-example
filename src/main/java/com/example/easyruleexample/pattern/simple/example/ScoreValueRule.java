package com.example.easyruleexample.pattern.simple.example;

import com.example.easyruleexample.pattern.DataHolder;
import com.example.easyruleexample.pattern.simple.SerialSingleValueRule;
import com.example.easyruleexample.supporting.CompareOperator;

public class ScoreValueRule extends SerialSingleValueRule<Integer, Integer> {
    private final Integer score;
    public ScoreValueRule(String name, CompareOperator operator, Integer threshold, Integer score) {
        super(name, operator, threshold);
        this.score = score;
    }

    @Override
    protected void action(DataHolder<Integer, Integer> dataHolder) {
        dataHolder.setOutput(score);
    }
}
