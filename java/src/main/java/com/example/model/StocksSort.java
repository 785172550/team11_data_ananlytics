package com.example.model;

/**
 * Created by Kenneth on 2017/8/9.
 */
public class StocksSort implements Comparable<StocksSort>{
    private Double value;
    private Stock stock;

    public StocksSort(Double value, Stock stock) {
        this.value = value;
        this.stock = stock;
    }

    public StocksSort() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
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
