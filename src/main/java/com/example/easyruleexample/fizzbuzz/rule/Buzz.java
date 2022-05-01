package com.example.easyruleexample.fizzbuzz.rule;

import org.jeasy.rules.annotation.*;

@Rule
public class Buzz {
    @Condition
    public boolean isBuzz(@Fact("number") Integer number) {
        return number % 7 == 0;
    }

    @Action
    public void printFizz(@Fact("number") Integer number ) {
//        System.out.println("buzz");
        System.out.printf("buzz(%s)", number);
    }

    @Priority
    public int getPriority() {
        return 2;
    }
}
