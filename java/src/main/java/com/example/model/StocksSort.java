package com.example.model;

import com.example.utils.DemoUtils;

/**
 * Created by Kenneth on 2017/8/9.
 */
public class StocksSort<T> implements Comparable<StocksSort>{
    private Double value;
    private T stock;

    public StocksSort(Double value, T stock) {
        this.value = value;
        this.stock = stock;
    }

    public StocksSort() {
    }

    public Double getValue() {
        return DemoUtils.get2Double(value);
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public T getStock() {
        return stock;
    }

    public void setStock(T stock) {
        this.stock = stock;
    }

    @Override
    public int compareTo(StocksSort o) {
        return this.value.compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return "StocksSort{" +
                "value=" + value +
                ", stock=" + stock +
                '}';
    }
}
