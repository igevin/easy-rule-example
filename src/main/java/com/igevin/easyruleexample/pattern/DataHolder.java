package com.igevin.easyruleexample.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataHolder<T extends Comparable<T>, V> {
    @Getter
    private static final String name = "dataHolder";
    private T input;
    private V output;

    public DataHolder(T input) {
        this.input = input;
    }
}
