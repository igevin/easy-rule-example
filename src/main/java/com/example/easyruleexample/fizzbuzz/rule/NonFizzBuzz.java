package com.example.easyruleexample.fizzbuzz.rule;

import org.jeasy.rules.annotation.*;

@Rule
public class NonFizzBuzz {
    @Condition
    public boolean isNotFizzNorBuzz(@Fact("number") Integer number) {
        return number % 5 != 0 || number % 7 != 0;
    }

    @Action
    public void printInput(@Fact("number") Integer number) {
        System.out.print(number);
    }

    @Priority
    public int getPriority() {
        return 3;
    }
}
