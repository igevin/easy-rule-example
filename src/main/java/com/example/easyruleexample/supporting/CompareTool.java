package com.example.easyruleexample.supporting;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Arrays;

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

    public static<T extends Comparable<T>> boolean compare(CompareOperator operator, T target, T valve) {
        switch (operator) {
            case GREATER_THAN: return valve.compareTo(target) < 0;
            case GREATER_THAN_OR_EQUAL_TO: return valve.compareTo(target) <= 0;
            case LESS_THAN: return valve.compareTo(target) > 0;
            case LESS_THAN_OR_EQUAL_TO: return valve.compareTo(target) >= 0;
            default: return valve.compareTo(target) == 0;
        }
    }

    @SneakyThrows
    public static boolean objectCompare(CompareOperator operator, Object target, Object valve) {
        Method[] methods = target.getClass().getMethods();
        Method compareTo = Arrays.stream(methods).filter(m -> m.getName().equals("compareTo")).findAny().orElse(null);
        if (compareTo == null) {
            throw new RuntimeException("target is not comparable");
        }
        int result = (int) compareTo.invoke(valve, target);
        switch (operator) {
            case GREATER_THAN: return result < 0;
            case GREATER_THAN_OR_EQUAL_TO: return result <= 0;
            case LESS_THAN: return result > 0;
            case LESS_THAN_OR_EQUAL_TO: return result >= 0;
            default: return result == 0;
        }
    }
}
