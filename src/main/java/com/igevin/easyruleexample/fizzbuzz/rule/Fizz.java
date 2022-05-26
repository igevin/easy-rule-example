package com.igevin.easyruleexample.fizzbuzz.rule;

import org.jeasy.rules.annotation.*;

@Rule
public class Fizz {

    @Condition
    public boolean isFizz(@Fact("number") Integer number) {
        return number % 5 == 0;
    }

    @Action
    public void printFizz(@Fact("number") Integer number) {
//        System.out.print("fizz");
        System.out.printf("fizz(%s)", number);
    }

    @Priority
    public int getPriority() {
        return 1;
    }
}
