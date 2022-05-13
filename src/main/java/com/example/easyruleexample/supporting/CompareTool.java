package com.example.easyruleexample.supporting;

public class CompareTool {
    public static boolean compare(CompareOperator operator, Integer target, Integer valve) {
        switch (operator) {
            case GREATER_THAN: return valve.compareTo(target) < 0;
            case GREATER_THAN_OR_EQUAL_TO: return valve.compareTo(target) <= 0;
            case LESS_THAN: return valve.compareTo(target) > 0;
            case LESS_THAN_OR_EQUAL_TO: return valve.compareTo(target) >= 0;
            default: return valve.compareTo(target) == 0;
        }
    }
}
