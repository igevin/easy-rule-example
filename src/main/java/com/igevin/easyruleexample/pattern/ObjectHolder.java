package com.igevin.easyruleexample.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectHolder<T, V> {
    @Getter
    private static final String name = "objectHolder";
    private T input;
    private V output;

    public ObjectHolder(T input) {
        this.input = input;
    }
}
