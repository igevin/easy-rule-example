package com.igevin.easyruleexample.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleTarget {
    @Getter
    private static final String name = "ruleTarget";
    private Integer value;
    private Integer score;

    public RuleTarget(int value) {
        this.value = value;
    }
}
