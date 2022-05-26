package com.igevin.easyruleexample.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 输入输出对象容器
 *
 * @param <T> 输入数据类型
 * @param <V> 输出数据类型
 */
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
