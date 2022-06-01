package com.igevin.easyruleexample.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 值输入输出容器
 *
 * @param <T> 输入值类型
 * @param <V> 返回结果类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueHolder<T extends Comparable<T>, V> {
    @Getter
    private static final String name = "dataHolder";
    private T input;
    private V output;

    public ValueHolder(T input) {
        this.input = input;
    }
}
